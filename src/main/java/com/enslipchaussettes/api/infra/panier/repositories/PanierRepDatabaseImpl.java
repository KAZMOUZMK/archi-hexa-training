package com.enslipchaussettes.api.infra.panier.repositories;

import com.enslipchaussettes.api.infra.panier.repositories.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.infra.panier.repositories.database.DatabaseSpringPanierRepository;
import com.enslipchaussettes.api.domain.produit.Catalogue;
import com.enslipchaussettes.api.domain.panier.Panier;
import com.enslipchaussettes.api.domain.panier.PanierRep;
import com.enslipchaussettes.api.domain.produit.Produit;
import com.enslipchaussettes.api.infra.dao.ArticleDao;
import com.enslipchaussettes.api.infra.dao.PanierDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PanierRepDatabaseImpl implements PanierRep {

    private Catalogue catalogue;

    private DatabaseSpringArticleRepository databaseSpringArticleRepository;

    @Autowired
    DatabaseSpringPanierRepository databaseSpringPanierRepository;

    public PanierRepDatabaseImpl(DatabaseSpringPanierRepository repositorypanier, DatabaseSpringArticleRepository repositoryArticle, Catalogue catalogue) {
        this.databaseSpringPanierRepository = repositorypanier;
        this.databaseSpringArticleRepository = repositoryArticle;
        this.catalogue = catalogue;
    }

    @Override
    public void savePanier(Panier panier) {
        Optional<PanierDao> panierDaoOptional = databaseSpringPanierRepository.findById(panier.uuid);
        PanierDao panierDao;

        if(panierDaoOptional.isEmpty()) {
            panierDao = new PanierDao();
            panierDao.setUuid(panier.uuid);
        } else {
            panierDao = panierDaoOptional.get();
            // On supprime les articles existants
            databaseSpringArticleRepository.deleteAll(panierDao.getArticles());
            panierDao.setArticles(new LinkedList<ArticleDao>());
        }
        List<ArticleDao> listeArticleDao = panier.showPanierAvecQuantite().stream().map(a -> new ArticleDao(a.getReference(), a.getQuantite(), panierDao)).toList();

        databaseSpringPanierRepository.save(panierDao);
        databaseSpringArticleRepository.saveAll(listeArticleDao);
    }

    @Override
    public Panier getPanier(UUID uuid) {
        Optional<PanierDao> optinalPanierDao = databaseSpringPanierRepository.findById(uuid);
        PanierDao panierDao = optinalPanierDao.get();
        Panier panier= new Panier(panierDao.getUuid());
        for(ArticleDao articleDao: panierDao.getArticles()){
            Produit produit = catalogue.getProduit(articleDao.getReference());
            panier.addQuantiteParProduit(articleDao.getQuantite(), produit);
        }
        return panier;
    }
}
