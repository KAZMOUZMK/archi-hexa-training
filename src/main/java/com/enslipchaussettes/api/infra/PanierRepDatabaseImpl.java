package com.enslipchaussettes.api.infra;

import com.enslipchaussettes.api.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.database.DatabaseSpringPanierRepository;
import com.enslipchaussettes.api.domain.Catalogue;
import com.enslipchaussettes.api.domain.Panier;
import com.enslipchaussettes.api.domain.PanierRep;
import com.enslipchaussettes.api.domain.Produit;
import com.enslipchaussettes.api.infra.dao.ArticleDao;
import com.enslipchaussettes.api.infra.dao.PanierDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            databaseSpringArticleRepository.deleteAll(panierDao.getArticles());
        }
        List<ArticleDao> listeArticleDao = panier.showPanierAvecQuantite().stream().map(a -> new ArticleDao(a.getReference(), a.getQuantite(), panierDao)).toList();
        panierDao.setArticles(listeArticleDao);
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
