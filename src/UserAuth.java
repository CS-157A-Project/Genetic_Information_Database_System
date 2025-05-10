// package src;
import java.sql.*;

public class UserAuth {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static class AuthResult {
        public final String role;
        public AuthResult(String role) {
            this.role = role;
        }
    }

    public static AuthResult login(String username, String password) {
        System.out.println("Attempting login with: " + username + " / " + password);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT role FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                System.out.println("✅ Login successful! Role: " + role);
                return new AuthResult(role);
            } else {
                System.out.println("❌ Login failed: No matching record.");
            }

        } catch (Exception e) {
            System.err.println("🚨 Error during login:");
            e.printStackTrace();
        }

        return null;
    }
}
