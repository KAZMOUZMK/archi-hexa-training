package com.enslipchaussettes.api.domain;

public class Article {

    private final String reference;

    private final int quantite;

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

    public Article ajoutQuantite2(int quantite) {
        return new Article(reference, this.quantite + quantite);
    }

    public Article incrementeQuantite2() {
        return new Article(reference, this.quantite + 1);
    }

    public Article decrementeQuantite2() {
        return new Article(reference, Math.max(this.quantite - 1, 0));
    }

    public boolean estZero() {
        return quantite == 0;
    }
}
