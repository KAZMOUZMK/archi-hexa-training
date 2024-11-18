package com.enslipchaussettes.api.domain;

import java.util.UUID;

public interface PanierRep {

    void savePanier(Panier panier);

    Panier getPanier(UUID uuid);

}
