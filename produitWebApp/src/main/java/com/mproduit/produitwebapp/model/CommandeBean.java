package com.mproduit.produitwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandeBean {
    private int id;
    private String description;
    private int quantite;
    private LocalDate date;
    private Double montant;


    private Long idProduit;
}
