package com.enslipchaussettes.api.domain;

public class ProduitAbsent extends RuntimeException {
    public ProduitAbsent(String sku) {
        super(String.format("Produit %s absent", sku));
    }
}
