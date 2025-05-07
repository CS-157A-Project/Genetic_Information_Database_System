import java.sql.*;
 import java.util.Scanner;
 
 public class UserAuth {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
     private static final String USER = "root";
     private static final String PASS = "123456"; // Replace with your MySQL root password
 
     public static class AuthResult {
         public final int userId;
         public final String role;
 
         public AuthResult(int userId, String role) {
             this.userId = userId;
             this.role = role;
         }
     }
 
     public static AuthResult login(Scanner scanner) {
         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
             System.out.print("Enter username: ");
             String username = scanner.nextLine();
             System.out.print("Enter password: ");
             String password = scanner.nextLine();
 
             String sql = "SELECT user_id, role FROM Users WHERE username = ? AND password = ?";
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setString(1, username);
             stmt.setString(2, password);
             ResultSet rs = stmt.executeQuery();
 
             if (rs.next()) {
                 int userId = rs.getInt("user_id");
                 String role = rs.getString("role");
                 System.out.println("Login successful. Role: " + role);
                 return new AuthResult(userId, role);
             } else {
                 System.out.println("Login failed. Invalid credentials.");
                 return null;
             }
         } catch (SQLException e) {
             e.printStackTrace();
             return null;
         }

      
        
     }
 }
 