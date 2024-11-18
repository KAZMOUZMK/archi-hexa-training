package com.enslipchaussettes.api;

import com.enslipchaussettes.api.domain.PanierRep;
import com.enslipchaussettes.api.domain.UtilisationPanier;
import com.enslipchaussettes.api.infra.PanierMemoire;

import java.util.List;
import java.util.UUID;

public class EConsole {

    public static void main(String[] args){
        PanierRep panierRep = new PanierMemoire();
        UtilisationPanier utilisationPanier = new UtilisationPanier(panierRep);
        UUID  uuid = utilisationPanier.initPanier();
        System.out.println(uuid);
        System.out.println("---");
        utilisationPanier.ajoutReference(uuid,"test");
        List<String> list = utilisationPanier.showReference(uuid);
        list.forEach(System.out::println);
        System.out.println("---");
        utilisationPanier.ajoutReference(uuid,"test2");
        List<String> list2 = utilisationPanier.showReference(uuid);
        list2.forEach(System.out::println);
        System.out.println("---");
        utilisationPanier.deleteReference(uuid,"test2");
        List<String> list3 = utilisationPanier.showReference(uuid);
        list3.forEach(System.out::println);
    }
}
