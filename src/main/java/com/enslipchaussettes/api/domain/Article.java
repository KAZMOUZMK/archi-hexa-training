package com.enslipchaussettes.api.domain;

public class Article {

    private String reference;

    private int quantite;

    public Article(String reference, int quantite) {
        this.reference = reference;
        this.quantite = quantite;
    }


    public String getReference() {
        return reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void incrementeQuantite() {
        this.quantite += 1;
    }

    public void decrementeQuantite() {
        this.quantite -= 1;
    }

    public void ajoutQuantite(int quantite) {
        this.quantite += quantite;
    }
}
