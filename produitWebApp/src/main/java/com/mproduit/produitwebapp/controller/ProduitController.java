package com.mproduit.produitwebapp.controller;



import com.mproduit.produitwebapp.model.CommandeBean;
import com.mproduit.produitwebapp.model.ProductBean;
import com.mproduit.produitwebapp.repository.MicroserviceCommandeProxy;
import com.mproduit.produitwebapp.repository.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@Controller
public class ProduitController {


    @Autowired
    private MicroserviceProduitsProxy microserviceProduitsProxy;
    @Autowired
    private MicroserviceCommandeProxy microserviceCommandeProxy;



    @RequestMapping("/")
    public String accueil(Model model){

        List<CommandeBean> commandes =  microserviceCommandeProxy.listCommandes();

        model.addAttribute("commandes", commandes);

        return "Accueil";
    }

    @RequestMapping("/details-commande/{id}")
    public String ficheCommande(@PathVariable int id, Model model){
        CommandeBean commande = microserviceCommandeProxy.recupererUneCommande(id);
        model.addAttribute("commande", commande);
        return "FicheProduit";
    }

    @RequestMapping(value = "/createCommande")
    public String ajouterCommande(@ModelAttribute CommandeBean commande,Model model){


        //appel du microservice commandes grâce à Feign et on récupère en retour les détails de la commande créée, notamment son ID (étape 4).
        CommandeBean commandeAjoutee = microserviceCommandeProxy.ajouterCommande(commande);
        //on passe à la vue l'objet commande et le montant de celle-ci afin d'avoir lesinformations nécessaire pour le paiement
        model.addAttribute("commandeAjoutee", commandeAjoutee);
        return "Add";
    }
    @RequestMapping ("/saveCommande")
    public String saveCommande(@ModelAttribute CommandeBean commande, Model model) {

        model.addAttribute("commande", commande);
        microserviceCommandeProxy.saveCommande(commande);

        return "Add";
    }

}