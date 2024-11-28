package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;

import java.util.List;

public interface UtilisationAdresse {
    List<RechercheAdresseResponse> chercherAdresse(String adresse);
}
