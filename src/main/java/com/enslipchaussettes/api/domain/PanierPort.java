package com.enslipchaussettes.api.domain;

import java.util.List;
import java.util.UUID;

public class PanierPort implements UtilisationPanier {
    PanierRep panierRep;
    @Override
    public UUID initPanier(){
        Panier panier = new Panier();
        panierRep.savePanier(panier);
        return panier.uuid;
    }

    public PanierPort(PanierRep panierRep){
        this.panierRep = panierRep;

    }


    @Override
    public void ajoutReference(UUID uuid, String reference){
        Panier  panier = panierRep.getPanier(uuid);
        panier.addReference(reference);
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
        panier.deleteRef(ref);
        panierRep.savePanier(panier);
    }

    @Override
    public void showReference(UUID uuid, PanierPresenter panierPresenter) {
        Panier  panier = panierRep.getPanier(uuid);
        panierPresenter.ajouterContenu(panier.showPanier());

    }
}
