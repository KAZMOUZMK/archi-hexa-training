package com.enslipchaussettes.api;

import com.enslipchaussettes.api.domain.*;
import com.enslipchaussettes.api.infra.CatalogueEnMemoire;
import com.enslipchaussettes.api.infra.ConsolePanierPresenter;
import com.enslipchaussettes.api.infra.PanierMemoire;

import java.util.List;
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
