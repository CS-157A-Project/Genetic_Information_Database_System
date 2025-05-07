CREATE DATABASE IF NOT EXISTS genetic_db;
USE genetic_db;

-- Users table
CREATE TABLE IF NOT EXISTS Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'researcher', 'staff') NOT NULL
);

-- Organisms table
CREATE TABLE IF NOT EXISTS Organisms (
    organism_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    classification VARCHAR(100)
);

-- Genes table
CREATE TABLE IF NOT EXISTS Genes (
    gene_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    function TEXT,
    organism_id INT,
    FOREIGN KEY (organism_id) REFERENCES Organisms(organism_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Mutations table
CREATE TABLE IF NOT EXISTS Mutations (
    mutation_id INT AUTO_INCREMENT PRIMARY KEY,
    gene_id INT,
    mutation_type VARCHAR(50),
    effect TEXT,
    FOREIGN KEY (gene_id) REFERENCES Genes(gene_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
