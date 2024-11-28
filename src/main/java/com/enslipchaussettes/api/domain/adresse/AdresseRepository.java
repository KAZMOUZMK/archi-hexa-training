package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;

import java.util.List;

public interface AdresseRepository {
    List<RechercheAdresseResponse> rechercheAdresse(String search);
}
