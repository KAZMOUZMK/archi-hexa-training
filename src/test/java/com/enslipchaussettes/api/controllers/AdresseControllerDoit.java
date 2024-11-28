package com.enslipchaussettes.api.controllers;

import com.enslipchaussettes.api.controllers.adresse.AdresseController;
import com.enslipchaussettes.api.domain.adresse.UtilisationAdresse;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AdresseControllerDoit {
    @Test
    public void appelle_le_use_case_pour_rechercher_les_adresses() {
        var utilisationAdresse = mock(UtilisationAdresse.class);
        var controller = new AdresseController(utilisationAdresse);

        controller.rechercheAdresse("adresse");
        verify(utilisationAdresse).chercherAdresse("adresse");
    }

    @Test
    public void appelle_le_use_case_pour_rechercher_detail_adresses() {
        var utilisationAdresse = mock(UtilisationAdresse.class);
        var controller = new AdresseController(utilisationAdresse);

        controller.rechercheDetailAdresse("placeId");
        verify(utilisationAdresse).recupererDetail("placeId");
    }
}
