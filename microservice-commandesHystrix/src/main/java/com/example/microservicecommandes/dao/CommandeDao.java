package com.example.microservicecommandes.dao;

import com.example.microservicecommandes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Integer> {

    @Query("SELECT c FROM Commande c ORDER BY c.date DESC")
    List<Commande> findLatestCommands();

    List<Commande> findByDateBetween(LocalDate debut,LocalDate end);
}
