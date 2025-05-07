CREATE DATABASE IF NOT EXISTS genetic_db;
USE genetic_db;

CREATE TABLE Organisms (
  organism_id    INT AUTO_INCREMENT PRIMARY KEY,
  scientific_name VARCHAR(100) NOT NULL,
  common_name     VARCHAR(100),
  taxonomy        VARCHAR(255),
  genome_size     INT
);
