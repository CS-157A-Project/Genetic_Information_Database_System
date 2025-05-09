// package src;

import java.sql.*;
import java.util.Scanner;

public class GeneticDBMain {
    // JDBC settings
    private static final String URL  = "jdbc:mysql://localhost:3306/genetic_db";
    private static final String USER = "root";
    private static final String PASS = "123456";  // your MySQL password

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 1) Prompt for login
        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();

        // 2) Authenticate
        UserAuth.AuthResult auth = UserAuth.login(username, password);
        if (auth == null) {
            System.out.println("Login failed.");
            return;
        }
        System.out.println("Hello, " + username + " (" + auth.role + ")");
        in.close();

        // 3) Connect and list organisms
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT organism_id, scientific_name FROM Organisms")) {

            System.out.println("\n--- Organisms in the Database ---");
            while (rs.next()) {
                System.out.printf("%d: %s%n",
                    rs.getInt("organism_id"),
                    rs.getString("scientific_name"));
            }

        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }
}

