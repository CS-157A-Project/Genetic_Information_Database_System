/*
 * GeneticDBMain.java
 * Entry point for the Genetic Information Database System
 */

 import java.sql.*;
 import java.util.Scanner;
 
 // Ensure UserAuth.java is in the same package or properly imported
 public class GeneticDBMain {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/genetic_db";
     private static final String USER = "root";
     private static final String PASS = "123456"; // Replace with your MySQL root password
 
     public static void main(String[] args) {
         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
             Scanner scanner = new Scanner(System.in);
 
             // Ask for username/password directly
             System.out.print("Enter username: ");
             String username = scanner.nextLine();
             System.out.print("Enter password: ");
             String password = scanner.nextLine();
 
             // Authenticate user
             UserAuth.AuthResult auth = UserAuth.login(username, password);
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
                     case 1:
                         viewOrganisms(conn);
                         break;
                     case 2:
                         viewGenes(conn);
                         break;
                     case 3:
                         viewMutations(conn);
                         break;
                     case 4:
                         if (!role.equals("researcher")) addOrganism(conn, scanner);
                         else System.out.println("Access denied.");
                         break;
                     case 5:
                         if (!role.equals("researcher")) addGene(conn, scanner);
                         else System.out.println("Access denied.");
                         break;
                     case 6:
                         if (!role.equals("researcher")) addMutation(conn, scanner);
                         else System.out.println("Access denied.");
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
 
     // (all the same method definitions: addOrganism, viewOrganisms, addGene, viewGenes, addMutation, viewMutations remain unchanged below)
     // For brevity, they are omitted in this snippet
 }
 
 /*
  * Note:
  * - Make sure MySQL is running and the schema `genetic_db` exists.
  * - Create the required tables in MySQL using the following SQL:
  *
  * CREATE TABLE Users (
  *   user_id INT AUTO_INCREMENT PRIMARY KEY,
  *   username VARCHAR(50) UNIQUE NOT NULL,
  *   password VARCHAR(255) NOT NULL,
  *   role ENUM('admin', 'researcher', 'staff') NOT NULL
  * );
  *
  * INSERT INTO Users (username, password, role) VALUES
  * ('admin', 'admin123', 'admin'),
  * ('alice', 'alice123', 'researcher'),
  * ('bob', 'bob123', 'staff');
  */