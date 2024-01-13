package com.example.microserviceproduits.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg){
        super("Not Found");
    }
}
