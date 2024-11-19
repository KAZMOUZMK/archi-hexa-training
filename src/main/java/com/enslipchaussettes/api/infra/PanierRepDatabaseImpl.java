package com.enslipchaussettes.api.infra;

import com.enslipchaussettes.api.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.database.DatabaseSpringPanierRepository;
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
    @Autowired
    DatabaseSpringPanierRepository databaseSpringPanierRepository;

    @Override
    public void savePanier(Panier panier) {
        PanierDao panierDao = new PanierDao();
        panierDao.setUuid(panier.uuid);
        List<ArticleDao> listeArticleDao= panier.showPanierAvecQuantite().stream().map(a-> new ArticleDao(a.getReference(), a.getQuantite())).toList();
        panierDao.setArticles(listeArticleDao);
        databaseSpringPanierRepository.save(panierDao);
    }

    @Override
    public Panier getPanier(UUID uuid) {
        Optional<PanierDao> optinalPanierDao = databaseSpringPanierRepository.findById(uuid);
        PanierDao panierDao = optinalPanierDao.get();
        Panier panier= new Panier(panierDao.getUuid());
        for(ArticleDao articleDao: panierDao.getArticles()){
            Produit produit = new Produit( articleDao.getReference(), 0);
            panier.addQuantiteParProduit(articleDao.getQuantite(), produit);
        }
        return panier;
    }
}
