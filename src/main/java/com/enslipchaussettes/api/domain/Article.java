package com.enslipchaussettes.api.domain;

public class Article {

    private final String reference;

    private final int quantite;

    private Produit produit;

    public Article(String reference, int quantite) {
        this.reference = reference;
        this.quantite = quantite;
        this.produit = null;
    }

    public Article(Produit produit, int quantite) {
        this.reference = null;
        this.quantite = quantite;
        this.produit= produit;
    }

    public String getReference() {
        if (produit == null) {
            return reference;
        }
        return produit.getReference();
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

    public boolean estProduit(Produit produit) {
        return produit.equals(this.produit);
    }
}
