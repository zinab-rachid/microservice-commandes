package com.example.microservicecommandes.Controller;

import com.example.microservicecommandes.configurations.ApplicationPropertiesConfiguration;
import com.example.microservicecommandes.dao.CommandeDao;
import com.example.microservicecommandes.exceptions.CommandeNotFoundException;
import com.example.microservicecommandes.model.Commande;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@EnableCircuitBreaker
@Configuration
@EnableHystrixDashboard
@RestController
public class CommandeController implements HealthIndicator {
    @Autowired
    CommandeDao commandeDao;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;
    // Affiche la liste de tous les commandes disponibles
    @GetMapping(value = "/Commandes")
    public List<Commande> listeDesCommandes() {
        List<Commande> commandes = commandeDao.findAll();
        if (commandes.isEmpty())
            throw new CommandeNotFoundException("Aucune commande n'est disponible ");
        LocalDate debut = LocalDate.now().minusDays(appProperties.getLast());
        LocalDate fin = LocalDate.now();
        return commandeDao.findByDateBetween(debut,fin);   }




    @GetMapping("/myMessage")

    @HystrixCommand(fallbackMethod = "myHistrixbuildFallbackMessage",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")},
            threadPoolKey = "messageThreadPool")

//    @HystrixCommand(fallbackMethod = "myHistrixbuildFallbackMessage",
//	commandProperties ={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
//
    public String getMessage() throws InterruptedException {
        System.out.println("Message from EmployeeController.getMessage(): Begin To sleep for 3 scondes ");
        Thread.sleep(3000);
        return "Message from EmployeeController.getMessage(): End from sleep for 3 scondes ";
    }

    private String myHistrixbuildFallbackMessage() {
        return "Message from myHistrixbuildFallbackMessage() : Hystrix Fallback message ( after timeout : 1 second )";
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

    @PostMapping("/commande")
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeDao.save(commande);
    }
    @DeleteMapping("/commande/{id}")
    public void deleteCommande(@PathVariable("id") final int id) {
        commandeDao.deleteById(id);
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
