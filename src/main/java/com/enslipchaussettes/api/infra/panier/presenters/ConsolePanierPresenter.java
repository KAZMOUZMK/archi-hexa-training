package com.enslipchaussettes.api.infra.panier.presenters;

import com.enslipchaussettes.api.domain.panier.PanierPresenter;

import java.util.List;

public class ConsolePanierPresenter implements PanierPresenter {

    private List<String> contenu;

    @Override
    public void ajouterContenu(List<String> references) {
        this.contenu = references;
    }

    @Override
    public void afficher() {
        System.out.println("---");
        this.contenu.forEach(System.out::println);

    }
}
