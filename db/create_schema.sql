-- Drop the database if it already exists to start fresh
DROP DATABASE IF EXISTS genetic_db;
CREATE DATABASE genetic_db;
USE genetic_db;

-- Create 'Users' table to store user login information and roles
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY, 
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'researcher', 'staff') NOT NULL
);

-- Create 'Organisms' table to store organism-related information
CREATE TABLE Organisms (
    organism_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    species VARCHAR(100),
    classification VARCHAR(100)
);

-- Create 'Genes' table to store gene information linked to organisms
CREATE TABLE Genes (
    gene_id INT AUTO_INCREMENT PRIMARY KEY,
    organism_id INT NOT NULL,
    gene_name VARCHAR(100) NOT NULL,
    sequence TEXT,
    FOREIGN KEY (organism_id) REFERENCES Organisms(organism_id)
);

-- Create 'Mutations' table to record genetic mutations
CREATE TABLE Mutations (
    mutation_id INT AUTO_INCREMENT PRIMARY KEY,
    gene_id INT NOT NULL,
    mutation_type VARCHAR(100),
    effect VARCHAR(255),
    FOREIGN KEY (gene_id) REFERENCES Genes(gene_id)
);
