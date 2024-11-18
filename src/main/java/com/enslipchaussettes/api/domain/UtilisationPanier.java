package com.enslipchaussettes.api.domain;

import java.util.List;
import java.util.UUID;

public class UtilisationPanier {
    PanierRep panierRep;
    public UUID initPanier(){
        Panier panier = new Panier();
        panierRep.savePanier(panier);
        return panier.uuid;
    }

    public UtilisationPanier(PanierRep panierRep){
        this.panierRep = panierRep;

    }


    public void ajoutReference(UUID uuid, String reference){
        Panier  panier = panierRep.getPanier(uuid);
        panier.addReference(reference);
        panierRep.savePanier(panier);
    }

    public List<String> showReference(UUID uuid){
        Panier  panier = panierRep.getPanier(uuid);
        return panier.showPanier();
    }

    public void deleteReference(UUID uuid, String ref){
        Panier  panier = panierRep.getPanier(uuid);
        panier.deleteRef(ref);
        panierRep.savePanier(panier);
    }
}
