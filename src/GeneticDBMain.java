package src;
import java.sql.*;
import java.util.Scanner;

public class GeneticDBMain {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
    private static final String USER = "root";
    private static final String PASS = "123456"; // your MySQL root password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Scanner scanner = new Scanner(System.in);

            // Login
            UserAuth.AuthResult auth = UserAuth.login(scanner);
            if (auth == null) {
                System.out.println("Exiting due to failed login.");
                return;
            }
            String role = auth.role;
            System.out.println("Welcome, " + role + " user!");

            while (true) {
                System.out.println("\nGenetic Information Database");
                System.out.println("1. View All Organisms");
                System.out.println("2. View All Genes");
                System.out.println("3. View All Mutations");

                if (!role.equals("researcher")) {
                    System.out.println("4. Add Organism");
                    System.out.println("5. Add Gene");
                    System.out.println("6. Add Mutation");
                }

                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> viewOrganisms(conn);
                    case 2 -> viewGenes(conn);
                    case 3 -> viewMutations(conn);
                    case 4 -> {
                        if (!role.equals("researcher")) addOrganism(conn, scanner);
                        else System.out.println("Access denied.");
                    }
                    case 5 -> {
                        if (!role.equals("researcher")) addGene(conn, scanner);
                        else System.out.println("Access denied.");
                    }
                    case 6 -> {
                        if (!role.equals("researcher")) addMutation(conn, scanner);
                        else System.out.println("Access denied.");
                    }
                    case 0 -> {
                        System.out.println("Exiting application.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addOrganism(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter scientific name: ");
        String scientificName = scanner.nextLine();
        System.out.print("Enter common name: ");
        String commonName = scanner.nextLine();
        System.out.print("Enter taxonomy: ");
        String taxonomy = scanner.nextLine();
        System.out.print("Enter genome size: ");
        int genomeSize = scanner.nextInt();
        scanner.nextLine();

        String sql = "INSERT INTO Organisms (scientific_name, common_name, taxonomy, genome_size) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, scientificName);
        stmt.setString(2, commonName);
        stmt.setString(3, taxonomy);
        stmt.setInt(4, genomeSize);
        stmt.executeUpdate();
        System.out.println("Organism added successfully.");
    }

    private static void viewOrganisms(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Organisms";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nOrganisms in Database:");
        while (rs.next()) {
            System.out.printf("ID: %d | Scientific: %s | Common: %s | Taxonomy: %s | Genome Size: %d%n",
                    rs.getInt("organism_id"), rs.getString("scientific_name"),
                    rs.getString("common_name"), rs.getString("taxonomy"), rs.getInt("genome_size"));
        }
    }

    private static void addGene(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter organism ID: ");
        int organismId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter gene name: ");
        String geneName = scanner.nextLine();
        System.out.print("Enter chromosome: ");
        String chromosome = scanner.nextLine();
        System.out.print("Enter start position: ");
        int start = scanner.nextInt();
        System.out.print("Enter end position: ");
        int end = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter strand (+ or -): ");
        String strand = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        String sql = "INSERT INTO Genes (organism_id, gene_name, chromosome, start_position, end_position, strand, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, organismId);
        stmt.setString(2, geneName);
        stmt.setString(3, chromosome);
        stmt.setInt(4, start);
        stmt.setInt(5, end);
        stmt.setString(6, strand);
        stmt.setString(7, description);
        stmt.executeUpdate();
        System.out.println("Gene added successfully.");
    }

    private static void viewGenes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Genes";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nGenes in Database:");
        while (rs.next()) {
            System.out.printf("ID: %d | Organism ID: %d | Gene Name: %s | Chromosome: %s | Range: %d-%d | Strand: %s | Description: %s%n",
                    rs.getInt("gene_id"), rs.getInt("organism_id"), rs.getString("gene_name"),
                    rs.getString("chromosome"), rs.getInt("start_position"), rs.getInt("end_position"),
                    rs.getString("strand"), rs.getString("description"));
        }
    }

    private static void addMutation(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter organism ID: ");
        int organismId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter chromosome: ");
        String chromosome = scanner.nextLine();
        System.out.print("Enter position: ");
        int position = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter reference base: ");
        String referenceBase = scanner.nextLine();
        System.out.print("Enter alternate base: ");
        String alternateBase = scanner.nextLine();
        System.out.print("Enter mutation type: ");
        String mutationType = scanner.nextLine();
        System.out.print("Enter impact: ");
        String impact = scanner.nextLine();

        String sql = "INSERT INTO Mutations (organism_id, chromosome, position, reference_base, alternate_base, mutation_type, impact) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, organismId);
        stmt.setString(2, chromosome);
        stmt.setInt(3, position);
        stmt.setString(4, referenceBase);
        stmt.setString(5, alternateBase);
        stmt.setString(6, mutationType);
        stmt.setString(7, impact);
        stmt.executeUpdate();
        System.out.println("Mutation added successfully.");
    }

    private static void viewMutations(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Mutations";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nMutations in Database:");
        while (rs.next()) {
            System.out.printf("ID: %d | Organism ID: %d | Chromosome: %s | Position: %d | Ref: %s | Alt: %s | Type: %s | Impact: %s%n",
                    rs.getInt("mutation_id"), rs.getInt("organism_id"), rs.getString("chromosome"),
                    rs.getInt("position"), rs.getString("reference_base"), rs.getString("alternate_base"),
                    rs.getString("mutation_type"), rs.getString("impact"));
        }
    }
}
