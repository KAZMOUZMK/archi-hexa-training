package com.enslipchaussettes.api;

import com.enslipchaussettes.api.domain.panier.PanierPort;
import com.enslipchaussettes.api.domain.panier.PanierPresenter;
import com.enslipchaussettes.api.domain.panier.PanierRep;
import com.enslipchaussettes.api.domain.panier.UtilisationPanier;
import com.enslipchaussettes.api.domain.produit.Catalogue;
import com.enslipchaussettes.api.infra.produit.CatalogueEnMemoire;
import com.enslipchaussettes.api.infra.panier.presenters.ConsolePanierPresenter;
import com.enslipchaussettes.api.infra.panier.repositories.PanierMemoire;

import java.util.UUID;

public class EConsole {

    public static void main(String[] args){
        PanierRep panierRep = new PanierMemoire();
        Catalogue catalogue = new CatalogueEnMemoire();

        UtilisationPanier utilisationPanier = new PanierPort(panierRep, catalogue);
        UUID  uuid = utilisationPanier.initPanier();
        System.out.println(uuid);

        utilisationPanier.ajoutReference(uuid,"slip-noir");
        PanierPresenter panierPresenter = new ConsolePanierPresenter();
        utilisationPanier.showReference(uuid, panierPresenter);
        panierPresenter.afficher();

        utilisationPanier.ajoutReference(uuid,"slip-blanc");
        utilisationPanier.showReference(uuid, panierPresenter);
        panierPresenter.afficher();

        utilisationPanier.deleteReference(uuid,"slip-noir");
        utilisationPanier.showReference(uuid, panierPresenter);
        panierPresenter.afficher();
    }
}
