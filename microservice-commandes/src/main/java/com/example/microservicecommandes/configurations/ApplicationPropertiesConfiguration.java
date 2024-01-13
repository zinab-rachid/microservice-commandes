package com.example.microservicecommandes.configurations;


import com.example.microservicecommandes.dao.CommandeDao;
import com.example.microservicecommandes.model.Commande;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties("mes-configs-ms.commandes")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    // correspond à la propriété «mes-config-ms.commandes-last » dans le fichier de configuration du MC
    private int last;
    private CommandeDao commandeDao;

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

}
