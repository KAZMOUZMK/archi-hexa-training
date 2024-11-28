package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.controllers.RechercheDetailAdresseResponse;

import java.util.List;

public interface AdresseRepository {
    List<RechercheAdresseResponse> rechercheAdresse(String search);

    RechercheDetailAdresseResponse recupererDetail(String placeId);

}
