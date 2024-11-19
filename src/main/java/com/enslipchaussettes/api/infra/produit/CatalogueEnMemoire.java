package com.enslipchaussettes.api.infra.produit;

import com.enslipchaussettes.api.domain.produit.Catalogue;
import com.enslipchaussettes.api.domain.produit.Produit;
import com.enslipchaussettes.api.domain.produit.ProduitAbsent;

import java.util.HashMap;

public class CatalogueEnMemoire implements Catalogue {
    private final HashMap<String, Produit> produits;

    public CatalogueEnMemoire() {
        this.produits = new HashMap<String, Produit>();
        this.produits.put("slip-noir", new Produit("slip-noir", 10));
        this.produits.put("slip-blanc", new Produit("slip-blanc", 20));

    }
    @Override
    public Produit getProduit(String sku) {
        var p =  produits.get(sku);
        if (p == null) {
            throw new ProduitAbsent(sku);
        }
        return p;
    }
}
