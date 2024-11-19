package com.enslipchaussettes.api.database;

import com.enslipchaussettes.api.container.TestPostgreSQLContainer;
import com.enslipchaussettes.api.domain.Panier;
import com.enslipchaussettes.api.domain.Produit;
import com.enslipchaussettes.api.infra.dao.PanierDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Testcontainers
class SpringPanierWithTestContainer {
    
    @Autowired
    private DatabaseSpringPanierRepository repository;
    
    @Container
    @ServiceConnection
    static TestPostgreSQLContainer postgres =
            TestPostgreSQLContainer.getInstance();

    @Test
    void pouvoir_recuperer_un_produit() {
        PanierDao panierDao = new PanierDao();
        UUID uuid = UUID.randomUUID();
        panierDao.setUuid(uuid);
        repository.save(panierDao);

        Optional<PanierDao> panierDaoOptional = repository.findById(uuid);
        PanierDao panierDaoFromDB = panierDaoOptional.get();

        Assertions.assertEquals(uuid, panierDaoFromDB.getUuid());
    }
}
