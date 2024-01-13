
CREATE TABLE product
(
    id          INT PRIMARY KEY,
    titre       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    image       VARCHAR(255) NOT NULL,
    prix        INT     NOT NULL
);
CREATE TABLE commande
(
    id          INT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    quantite       INT NOT NULL,
    date    DATE NOT NULL,
    montant DOUBLE NOT NULL,
    produit INT,
    FOREIGN KEY (produit) REFERENCES product(id)
);
