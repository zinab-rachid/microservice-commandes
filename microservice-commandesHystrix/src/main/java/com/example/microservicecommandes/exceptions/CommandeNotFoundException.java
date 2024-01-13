package com.example.microservicecommandes.exceptions;

public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException(String msg){
        super("Not Found");
    }
}
