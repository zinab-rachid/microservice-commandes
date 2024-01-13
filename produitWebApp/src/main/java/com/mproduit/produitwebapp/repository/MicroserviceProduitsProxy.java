package com.mproduit.produitwebapp.repository;

import com.mproduit.produitwebapp.model.CommandeBean;
import com.mproduit.produitwebapp.model.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microserviceproduits", url = "localhost:9001")
public interface MicroserviceProduitsProxy {
    @GetMapping(value = "/Produits")
    List<ProductBean> listeDesProduits();
    @GetMapping( value = "/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);


}
