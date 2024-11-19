package com.enslipchaussettes.api.domain.produit;

import java.util.Objects;

public class Produit {
    private final String sku;
    private final int prix;
    public Produit(String sku, int prix) {
        this.sku = sku;
        this.prix = prix;
    }

    public String getReference() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return prix == produit.prix && Objects.equals(sku, produit.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, prix);
    }
}
