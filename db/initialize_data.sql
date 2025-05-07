USE genetic_db;

-- Users
INSERT IGNORE INTO Users (username, password, role) VALUES
  ('admin', 'admin123', 'admin'),
  ('alice', 'alice123', 'researcher'),
  ('bob', 'bob123', 'staff'),
  ('carol', 'carol123', 'staff'),
  ('dave', 'dave123', 'researcher'),
  ('emma', 'emma123', 'staff'),
  ('frank', 'frank123', 'admin'),
  ('grace', 'grace123', 'researcher'),
  ('hugo', 'hugo123', 'staff'),
  ('ivy', 'ivy123', 'admin'),
  ('jake', 'jake123', 'researcher'),
  ('kate', 'kate123', 'staff'),
  ('leo', 'leo123', 'admin'),
  ('mona', 'mona123', 'researcher'),
  ('nina', 'nina123', 'staff');

-- Organisms
INSERT IGNORE INTO Organisms (name, classification) VALUES
  ('E. coli', 'Bacteria'),
  ('Homo sapiens', 'Mammal'),
  ('Saccharomyces cerevisiae', 'Fungus'),
  ('Drosophila melanogaster', 'Insect'),
  ('Arabidopsis thaliana', 'Plant');

-- Genes
INSERT IGNORE INTO Genes (organism_id, gene_name, sequence) VALUES
  (1, 'lacZ', 'Beta-galactosidase'),
  (2, 'BRCA1', 'DNA repair gene'),
  (3, 'GAL1', 'Galactokinase'),
  (4, 'eyeless', 'Transcription factor'),
  (5, 'FT', 'Flowering gene');

-- Mutations
INSERT IGNORE INTO Mutations (gene_id, mutation_type, effect) VALUES
  (1, 'deletion', 'loss of function'),
  (2, 'missense', 'partial function'),
  (3, 'nonsense', 'truncated protein'),
  (4, 'insertion', 'frame shift'),
  (5, 'duplication', 'gain of function');
