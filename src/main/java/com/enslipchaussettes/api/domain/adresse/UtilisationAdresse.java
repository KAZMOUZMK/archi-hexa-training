package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;

public interface UtilisationAdresse {
    RechercheAdresseResponse chercherAdresse(String adresse);
}
