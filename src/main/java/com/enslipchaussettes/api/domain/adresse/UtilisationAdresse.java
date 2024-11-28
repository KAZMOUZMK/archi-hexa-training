package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.controllers.RechercheDetailAdresseResponse;

import java.util.List;

public interface UtilisationAdresse {
    List<RechercheAdresseResponse> chercherAdresse(String adresse);

    public RechercheDetailAdresseResponse recupererDetail(String placeId);
}
