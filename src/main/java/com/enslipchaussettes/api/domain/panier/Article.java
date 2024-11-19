package com.enslipchaussettes.api.domain.panier;

import com.enslipchaussettes.api.domain.produit.Produit;

public class Article {

    private final String reference;

    private final int quantite;

    private Produit produit;

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

    public Article ajoutQuantite(int quantite) {
        return new Article(produit, this.quantite + quantite);
    }

    public Article incrementeQuantite() {
        return new Article(produit, this.quantite + 1);
    }

    public Article decrementeQuantite() {
        return new Article(produit, Math.max(this.quantite - 1, 0));
    }

    public boolean estZero() {
        return quantite == 0;
    }

    public boolean estProduit(Produit produit) {
        return produit.equals(this.produit);
    }
}
