package com.mproduit.produitwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProduitWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitWebAppApplication.class, args);
    }

}
