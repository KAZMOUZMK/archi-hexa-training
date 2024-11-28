package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.controllers.RechercheDetailAdresseResponse;

import java.util.List;

public class AdressePort implements UtilisationAdresse{
    private final AdresseRepository repo;

    public AdressePort(AdresseRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<RechercheAdresseResponse> chercherAdresse(String adresse) {
        return repo.rechercheAdresse(adresse);
    }

    @Override
    public RechercheDetailAdresseResponse recupererDetail(String placeId) {
        return repo.recupererDetail(placeId);
    }
}
