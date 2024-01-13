CREATE TABLE commande
(
    id          INT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    quantite       INT NOT NULL,
    date    DATE NOT NULL,
    montant DOUBLE NOT NULL,
    produit INT

);
