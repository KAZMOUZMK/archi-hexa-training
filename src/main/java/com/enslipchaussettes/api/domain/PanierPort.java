package com.enslipchaussettes.api.domain;

import java.util.List;
import java.util.UUID;

public class PanierPort implements UtilisationPanier {
    private  Catalogue catalogue = null;
    private final PanierRep panierRep;

    public PanierPort(PanierRep panierRep, Catalogue catalogue) {
        this.panierRep = panierRep;
        this.catalogue = catalogue;
    }

    @Override
    public UUID initPanier(){
        Panier panier = new Panier();
        panierRep.savePanier(panier);
        return panier.uuid;
    }

    @Override
    public void ajoutReference(UUID uuid, String reference){
        Panier  panier = panierRep.getPanier(uuid);
        var produit = catalogue.getProduit(reference);
        panier.addProduit(produit);
        panierRep.savePanier(panier);
    }

    @Override
    public List<String> showReference(UUID uuid){
        Panier  panier = panierRep.getPanier(uuid);
        return panier.showPanier();
    }

    @Override
    public void deleteReference(UUID uuid, String ref){
        Panier  panier = panierRep.getPanier(uuid);
        var produit = catalogue.getProduit(ref);
        panier.deleteProduit(produit);
        panierRep.savePanier(panier);
    }

    @Override
    public void showReference(UUID uuid, PanierPresenter panierPresenter) {
        Panier  panier = panierRep.getPanier(uuid);
        panierPresenter.ajouterContenu(panier.showPanier());

    }
}
