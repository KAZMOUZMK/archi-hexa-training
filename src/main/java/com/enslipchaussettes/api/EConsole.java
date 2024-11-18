package com.enslipchaussettes.api;

import com.enslipchaussettes.api.domain.PanierRep;
import com.enslipchaussettes.api.domain.PanierPort;
import com.enslipchaussettes.api.domain.UtilisationPanier;
import com.enslipchaussettes.api.infra.ConsolePanierPresenter;
import com.enslipchaussettes.api.infra.PanierMemoire;
import com.enslipchaussettes.api.domain.PanierPresenter;

import java.util.List;
import java.util.UUID;

public class EConsole {

    public static void main(String[] args){
        PanierRep panierRep = new PanierMemoire();
        UtilisationPanier utilisationPanier = new PanierPort(panierRep);
        UUID  uuid = utilisationPanier.initPanier();
        System.out.println(uuid);

        utilisationPanier.ajoutReference(uuid,"test");
        PanierPresenter panierPresenter = new ConsolePanierPresenter();
        utilisationPanier.showReference(uuid, panierPresenter);
        panierPresenter.afficher();

        utilisationPanier.ajoutReference(uuid,"test2");
        utilisationPanier.showReference(uuid, panierPresenter);
        panierPresenter.afficher();

        utilisationPanier.deleteReference(uuid,"test2");
        utilisationPanier.showReference(uuid, panierPresenter);
        panierPresenter.afficher();
    }
}
