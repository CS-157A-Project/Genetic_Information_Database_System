// package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAuth {

    // Adjust your DB connection as needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
    private static final String USER = "root";
    private static final String PASS = "123456";

    // Simple result class to return login status and role
    public static class AuthResult {
        public final String role;
        public AuthResult(String role) {
            this.role = role;
        }
    }

    // Login method
    public static AuthResult login(String username, String password) {
        System.out.println("Attempting login with: " + username + " / " + password);  // Debug input
    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT role FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                String role = rs.getString("role");
                System.out.println("Login success! Role: " + role);  // Debug result
                return new AuthResult(role);
            } else {
                System.out.println("Login failed: No matching user found.");  // Debug failure
            }
        } catch (Exception e) {
            System.err.println("Error during login:");
            e.printStackTrace();  // This will show SQLException or connection issues
        }
    
        return null;
    }
}
