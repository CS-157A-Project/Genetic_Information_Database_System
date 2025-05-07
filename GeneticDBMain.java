
import java.sql.*;
import java.util.Scanner;
 
public class GeneticDBMain {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
     private static final String USER = "root";
     private static final String PASS = "yourpassword"; // Replace with your MySQL root password
 
     public static void main(String[] args) {
         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
             System.out.println("Connected to MySQL Database.");
             Scanner scanner = new Scanner(System.in);
             
             while (true) {
                 System.out.println("\nGenetic Information Database");
                 System.out.println("1. Add Organism");
                 System.out.println("2. View All Organisms");
                 System.out.println("0. Exit");
                 System.out.print("Choose an option: ");
                 int choice = scanner.nextInt();
                 scanner.nextLine();
 
                 switch (choice) {
                     case 1:
                         addOrganism(conn, scanner);
                         break;
                     case 2:
                         viewOrganisms(conn);
                         break;
                     case 0:
                         System.out.println("Exiting application.");
                         return;
                     default:
                         System.out.println("Invalid choice.");
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
             System.out.printf("ID: %d | Scientific: %s | Common: %s | Taxonomy: %s | Genome Size: %d\n",
                     rs.getInt("organism_id"), rs.getString("scientific_name"), rs.getString("common_name"),
                     rs.getString("taxonomy"), rs.getInt("genome_size"));
         }
     }
 }
 