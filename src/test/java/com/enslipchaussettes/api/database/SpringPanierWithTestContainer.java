package com.enslipchaussettes.api.database;

import com.enslipchaussettes.api.infra.dao.ArticleDao;
import com.enslipchaussettes.api.infra.dao.PanierDao;
import com.enslipchaussettes.api.infra.panier.repositories.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.infra.panier.repositories.database.DatabaseSpringPanierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Testcontainers
class SpringPanierWithTestContainer {
    
    @Autowired
    private DatabaseSpringPanierRepository repositorypanier;

    @Autowired
    private DatabaseSpringArticleRepository repositoryArticle;
    
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Test
    void pouvoir_recuperer_un_panier() {
        PanierDao panierDao = new PanierDao();
        UUID uuid = UUID.randomUUID();
        panierDao.setUuid(uuid);
        repositorypanier.save(panierDao);

        Optional<PanierDao> panierDaoOptional = repositorypanier.findById(uuid);
        PanierDao panierDaoFromDB = panierDaoOptional.get();

        Assertions.assertEquals(uuid, panierDaoFromDB.getUuid());
    }

    @Test
    void pouvoir_recuperer_des_articles_de_un_panier() {
        PanierDao panierDao = new PanierDao();
        UUID uuid = UUID.randomUUID();
        panierDao.setUuid(uuid);
        repositorypanier.save(panierDao);
        ArticleDao articleDao =new ArticleDao();
        articleDao.setQuantite(1);
        articleDao.setPanier(panierDao);
        articleDao.setReference("ref1");
        repositoryArticle.save(articleDao);
        Optional<PanierDao> panierDaoOptional = repositorypanier.findById(uuid);
        PanierDao panierDaoFromDB = panierDaoOptional.get();
        List<ArticleDao> list = panierDaoFromDB.getArticles();


        Assertions.assertEquals(uuid, panierDaoFromDB.getUuid());
        Assertions.assertEquals(list.getFirst().getReference(), "ref1");
    }
}
