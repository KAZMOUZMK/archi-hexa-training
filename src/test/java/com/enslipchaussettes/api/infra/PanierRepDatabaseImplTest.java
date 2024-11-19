package com.enslipchaussettes.api.infra;

import com.enslipchaussettes.api.container.TestPostgreSQLContainer;
import com.enslipchaussettes.api.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.database.DatabaseSpringPanierRepository;
import com.enslipchaussettes.api.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Testcontainers
public class PanierRepDatabaseImplTest {

    @Autowired
    private DatabaseSpringPanierRepository repositorypanier;

    @Autowired
    private DatabaseSpringArticleRepository repositoryArticle;

    @Container
    @ServiceConnection
    static TestPostgreSQLContainer postgres =
            TestPostgreSQLContainer.getInstance();

    private PanierRep panierRep;


    @BeforeEach
    void init() {
        panierRep = new PanierRepDatabaseImpl(repositorypanier, repositoryArticle,new CatalogueEnMemoire());
    }

    @Test
    void pouvoir_sauvegarder_un_panier() {
        Panier panier = new Panier();
        Produit produit = new Produit("slip-noir", 10);
        panier.addQuantiteParProduit(1, produit);

        panierRep.savePanier(panier);
        Panier panierRecuperer = panierRep.getPanier(panier.uuid);

        assertEquals(panier.uuid, panierRecuperer.uuid);
        List<Article> articleList = panierRecuperer.showPanierAvecQuantite();

        assertEquals(1 , articleList.size());
        assertEquals("slip-noir", articleList.getFirst().getReference());

    }

    @Test
    void pouvoir_sauvegarder_un_panier_avec_deux_references_identiques() {
        Panier panier = new Panier();
        Produit produit = new Produit("slip-noir", 10);
        panier.addQuantiteParProduit(1, produit);

        panierRep.savePanier(panier);
        panier.addQuantiteParProduit(3, produit);
        panierRep.savePanier(panier);

        Panier panierRecuperer = panierRep.getPanier(panier.uuid);

        assertEquals(panier.uuid, panierRecuperer.uuid);
        List<Article> articleList = panierRecuperer.showPanierAvecQuantite();

        assertEquals(1 , articleList.size());
        assertEquals("slip-noir", articleList.getFirst().getReference());
        assertEquals(4, articleList.getFirst().getQuantite());

    }

}
