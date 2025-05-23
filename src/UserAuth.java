// Import standard SQL classes for database connectivity
import java.sql.*;

public class UserAuth {
    // Database connection constants
    private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
    private static final String USER = "root";
    private static final String PASS = "123456";

    // Class to store login result
    public static class AuthResult {
        public final String role;
        public AuthResult(String role) {
            this.role = role;
        }
    }

    // Method to validate login credentials
    public static AuthResult login(String username, String password) {
        System.out.println("Attempting login with: " + username + " / " + password);

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String sql = "SELECT role FROM Users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");
                    System.out.println("Login successful! Role: " + role);
                    return new AuthResult(role);
                } else {
                    System.out.println("Login failed: No matching record.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error during login:");
            e.printStackTrace();
        }

        return null;
    }
}
