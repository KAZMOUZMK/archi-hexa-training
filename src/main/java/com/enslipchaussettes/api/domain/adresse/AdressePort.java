package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;

public class AdressePort implements UtilisationAdresse{
    private final AdresseRepository repo;

    public AdressePort(AdresseRepository repo) {
        this.repo = repo;
    }

    @Override
    public RechercheAdresseResponse chercherAdresse(String adresse) {
        return repo.rechercheAdresse(adresse);
    }
}
