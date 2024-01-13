package com.example.microservicecommandes.Controller;

import com.example.microservicecommandes.configurations.ApplicationPropertiesConfiguration;
import com.example.microservicecommandes.dao.CommandeDao;
import com.example.microservicecommandes.exceptions.CommandeNotFoundException;
import com.example.microservicecommandes.model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
public class CommandeController implements HealthIndicator {
    @Autowired
    CommandeDao commandeDao;


    @Autowired
    ApplicationPropertiesConfiguration appProperties;
    @GetMapping(value = "/Commandes")
    public List<Commande> listCommandes() {
        List<Commande> commandes = commandeDao.findAll();
        if (commandes.isEmpty())
            throw new CommandeNotFoundException("Aucune commande n'est disponible ");

        return commandes;
    }

    // Affiche la liste de tous les commandes disponibles
    @GetMapping(value = "/listCommandes")
    public List<Commande> listeDesCommandes() {
        List<Commande> commandes = commandeDao.findAll();
        if (commandes.isEmpty())
            throw new CommandeNotFoundException("Aucune commande n'est disponible ");
        LocalDate debut = LocalDate.now().minusDays(appProperties.getLast());
        LocalDate fin = LocalDate.now();
        return commandeDao.findByDateBetween(debut,fin);   }


    @PostMapping (value = "/ajouterCommandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandeDao.save(commande);

        if(nouvelleCommande == null) throw new CommandeNotFoundException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }
    // Récuperer une commnde par son id
    @GetMapping(value = "/Commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id) {
        System.out.println(" ********* CommandeController recupererUneCommande(@PathVariable int id) ");
        Optional<Commande> commande = commandeDao.findById(id);
        if (!commande.isPresent())
            throw new CommandeNotFoundException("La commande correspondant à l'id " + id + " n'existe pas");
        return commande;
    }


    @DeleteMapping("/deleteCommande/{id}")
    public void deleteCommande(@PathVariable("id") final int id) {
        commandeDao.deleteById(id);
    }
    @PutMapping(value = "/saveCommande")
    public void saveCommande(@RequestBody Commande commande) {

        commandeDao.save(commande);
    }

    @Override
    public Health health() {
        System.out.println("****** Actuator : CommandeController health() ");
        List<Commande> commandes = commandeDao.findAll();
        if (commandes.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
