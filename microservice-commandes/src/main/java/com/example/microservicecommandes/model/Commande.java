package com.example.microservicecommandes.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Commande {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    private int quantite;
    private LocalDate date;
    private Double montant;

    @Column(name = "PRODUIT")

    private Long idProduit;

    public Commande(){
    }
    public Commande(int id, String description, int quantite, LocalDate date, Double montant,Long idProduit){
        this.id = id; this.description = description; this.quantite = quantite; this.date = date; this.montant = montant;this.idProduit = idProduit;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", description='" + description + '\'' + ", quantité='" + quantite + '\'' + ", Date=" + date + ", Montant=" + montant+ '}'; }
}
