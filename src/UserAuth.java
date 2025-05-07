package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuth {
    // Inner class to hold the result of an authentication
    public static class AuthResult {
        public final String role;
        public AuthResult(String role) { this.role = role; }
    }

    // Authenticate against the Users table in genetic_db
    public static AuthResult login(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/genetic_db";
        String dbUser = "root";
        String dbPass = "123456";  // your MySQL password

        String sql = "SELECT role FROM Users WHERE username = ? AND password = ?";
        try (
            Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AuthResult(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // login failed
    }
}

