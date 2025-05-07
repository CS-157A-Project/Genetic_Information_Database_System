USE genetic_db;

-- Users
INSERT INTO Users (username, password, role) VALUES
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
INSERT INTO Organisms (name, classification) VALUES
('E. coli', 'Bacteria'),
('Homo sapiens', 'Mammal'),
('Saccharomyces cerevisiae', 'Fungus'),
('Drosophila melanogaster', 'Insect'),
('Arabidopsis thaliana', 'Plant'),
('Mus musculus', 'Mammal'),
('Zea mays', 'Plant'),
('Caenorhabditis elegans', 'Nematode'),
('Bos taurus', 'Mammal'),
('Canis lupus', 'Mammal'),
('Felis catus', 'Mammal'),
('Rattus norvegicus', 'Rodent'),
('Gallus gallus', 'Bird'),
('Danio rerio', 'Fish'),
('Oryza sativa', 'Plant');

-- Genes
INSERT INTO Genes (name, function, organism_id) VALUES
('lacZ', 'Beta-galactosidase', 1),
('BRCA1', 'DNA repair', 2),
('CDC28', 'Cell cycle regulation', 3),
('Notch', 'Signaling', 4),
('FT', 'Flowering time regulation', 5),
('p53', 'Tumor suppression', 2),
('Actin', 'Cytoskeleton structure', 6),
('rbcL', 'Photosynthesis enzyme', 5),
('EGFR', 'Cell signaling', 2),
('MYC', 'Transcription factor', 2),
('FAD2', 'Fatty acid synthesis', 7),
('unc-54', 'Muscle protein', 8),
('OXTR', 'Oxytocin receptor', 10),
('TP53', 'Tumor suppressor', 12),
('CRY1', 'Circadian clock gene', 14);

-- Mutations
INSERT INTO Mutations (gene_id, mutation_type, effect) VALUES
(1, 'insertion', 'Loss of function'),
(2, 'deletion', 'Increased cancer risk'),
(3, 'missense', 'Slower cell division'),
(4, 'nonsense', 'Protein truncation'),
(5, 'duplication', 'Early flowering'),
(6, 'deletion', 'No tumor suppression'),
(7, 'frameshift', 'Disrupted cytoskeleton'),
(8, 'missense', 'Reduced photosynthesis'),
(9, 'amplification', 'Overactive cell growth'),
(10, 'missense', 'Uncontrolled proliferation'),
(11, 'point', 'Altered lipid composition'),
(12, 'deletion', 'Muscle twitching'),
(13, 'missense', 'Behavioral changes'),
(14, 'frameshift', 'Nonfunctional protein'),
(15, 'deletion', 'Disrupted sleep cycles');
