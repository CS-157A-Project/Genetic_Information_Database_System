package src;

import java.sql.*;
import java.util.Scanner;

/**
 * Simple console app for Genetic Information Database.
 * Connects to MySQL, lists all organisms, then exits.
 */
public class GeneticDBMain {
    // JDBC URL, username and password of MySQL server
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/genetic_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; // your MySQL root password

    public static void main(String[] args) {
        // Scanner for optional future input
        Scanner scanner = new Scanner(System.in);

        // 1) Load driver (JDBC 4.x auto-loads, but explicit is harmless)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            return;
        }

        // 2) Connect & query
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("Connected to genetic_db.");

            // Simple menu
            System.out.println("\n=== Organisms in Database ===");
            String sql = "SELECT organism_id, scientific_name, common_name FROM Organisms";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id   = rs.getInt("organism_id");
                    String sci = rs.getString("scientific_name");
                    String com = rs.getString("common_name");
                    System.out.printf("[%d] %s (%s)%n", id, sci, com);
                }
            }

            System.out.println("\nDone. Goodbye!");
        }
        catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
    }
}

