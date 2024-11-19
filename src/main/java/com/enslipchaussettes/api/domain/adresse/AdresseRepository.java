package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;

public interface AdresseRepository {
    RechercheAdresseResponse rechercheAdresse(String adresse);
}
