# Genetic_Information_Database_System
👥 Team Members
Athish Kumar – athish.kumar@sjsu.edu
Maria Kuznetsova – maria.kuznetsova@sjsu.edu
Huy Tran – ducbaohuy.tran@sjsu.edu

Instructor: Dr. Tahereh Arabghalizi
Date: May 9, 2025

# 🧬 Genetic Information Database System
A web-based platform designed to store and manage genetic data related to organisms, genes, sequences, and mutations. This tool is aimed at supporting researchers and bioinformatics staff in efficiently handling and querying biological data.

---

## 📌 Project Overview
This system enables:
- Storing structured genetic data
- Managing users and access roles
- Running SQL queries and CRUD operations via a clean, servlet-based web interface
It follows a **three-tier architecture**:
1. **Presentation Layer** – HTML + Tailwind CSS
2. **Logic Layer** – Java Servlets + JDBC
3. **Data Layer** – MySQL relational schema
---
## 🛠️ Technologies Used
| Component        | Technology             |
|------------------|------------------------|
| Frontend         | HTML5, Tailwind CSS    |
| Backend          | Java Servlets (Jakarta API) |
| Database         | MySQL                  |
| Deployment       | Apache Tomcat 10.x     |
| DB Connectivity  | JDBC                   |
| Version Control  | GitHub                 |

---

## 🗂️ Directory Structure
geneticdb/
├── login.html # User login form
├── dashboard.html # User dashboard (optional)
├── WEB-INF/
│ ├── web.xml # Servlet configuration
│ ├── lib/
│ │ ├── mysql-connector-j-9.3.0.jar
│ │ └── jakarta.servlet-api-6.0.0.jar
│ └── classes/
│ ├── LoginServlet.java
│ ├── DashboardServlet.java
│ └── UserAuth.java
├── db/
│ ├── create_schema.sql # Database schema setup
│ └── initialize_data.sql # Test data (15+ rows/table)
└── README.md

🚀 How to Run the Project
1. Create Database
mysql -u root -p < db/create_schema.sql
mysql -u root -p < db/initialize_data.sql
2. Compile Java Code
javac -cp "WEB-INF/lib/*" -d WEB-INF/classes src/*.java
3. Package WAR
jar -cvf geneticdb.war *
4. Deploy on Tomcat
Copy geneticdb.war to tomcat/webapps/
Run startup.sh from tomcat/bin/
5. Access Web App
http://localhost:8080/geneticdb/login.html


 👨‍💻 Features
User Login & Session Tracking
Add, View, Update, and Delete:
Organisms
Genes
Mutations
Custom SQL Query Support (via backend or terminal)
JDBC error handling and security via prepared statements

🧠 Future Enhancements
UI enhancements for gene/mutation search
Advanced filtering and visual analytics
Full admin dashboard with user management
Integration with external genetic data APIs




