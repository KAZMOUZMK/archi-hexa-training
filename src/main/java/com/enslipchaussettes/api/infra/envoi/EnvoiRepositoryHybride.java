package com.enslipchaussettes.api.infra.envoi;

import com.enslipchaussettes.api.domain.envoi.Envoi;
import com.enslipchaussettes.api.domain.envoi.EnvoiRep;
import com.enslipchaussettes.api.domain.panier.PanierRep;
import com.enslipchaussettes.api.infra.panier.repositories.PanierRepDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

public class EnvoiRepositoryHybride implements EnvoiRep {

    private static EnvoiRepositoryHybride instance = null;
    private final HashMap<UUID, Envoi> envois;

    @Autowired
    private final PanierRep panierRep;

    public EnvoiRepositoryHybride(PanierRep panierRep) {
        this.envois = new HashMap<UUID, Envoi>();
        this.panierRep = panierRep;
    }

    public static EnvoiRepositoryHybride getInstance(PanierRep panierRepository) {
        if (instance == null) {
           instance = new EnvoiRepositoryHybride(panierRepository);
        }
        return instance;
    }

    @Override
    public void saveEnvoi(Envoi envoi) {
        panierRep.savePanier(envoi.getPanier());
        this.envois.put(envoi.getId(), envoi);
    }

    @Override
    public Envoi recupererEnvoi(UUID id) {
        return this.envois.get(id);
    }
}
