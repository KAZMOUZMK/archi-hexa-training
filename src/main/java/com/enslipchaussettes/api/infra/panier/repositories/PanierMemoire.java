package com.enslipchaussettes.api.infra.panier.repositories;

import com.enslipchaussettes.api.domain.panier.Panier;
import com.enslipchaussettes.api.domain.panier.PanierRep;

import java.util.HashMap;
import java.util.UUID;

public class PanierMemoire implements PanierRep {

    private final HashMap<UUID, Panier> paniers;

    @Override
    public void savePanier(Panier panier) {
        this.paniers.put(panier.uuid, panier);
    }

    @Override
    public Panier getPanier(UUID uuid) {
        return this.paniers.get(uuid);
    }

    public PanierMemoire(){
        this.paniers = new HashMap<UUID, Panier>();
    }
}
