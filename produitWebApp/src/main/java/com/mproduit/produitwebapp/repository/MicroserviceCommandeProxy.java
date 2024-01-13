package com.mproduit.produitwebapp.repository;

import com.mproduit.produitwebapp.model.CommandeBean;
import com.mproduit.produitwebapp.model.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-commandes", url = "localhost:9010")

public interface MicroserviceCommandeProxy {
    @PostMapping(value = "/ajouterCommandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);

    @PutMapping(value = "/saveCommande")
    public void saveCommande(@RequestBody CommandeBean commande);

    @GetMapping(value = "/Commandes")
    List<CommandeBean> listCommandes();
    @GetMapping( value = "/Commandes/{id}")
    CommandeBean recupererUneCommande(@PathVariable("id") int id);
}
