package com.enslipchaussettes.api.controllers;

import com.enslipchaussettes.api.controllers.adresse.AdresseController;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AdresseControllerDoit {
    @Test
    public void appelle_le_use_case_pour_rechercher_les_adresses() {
        var utilisationAdresse = mock(com.enslipchaussettes.api.domain.adresse.UtilisationAdresse.class);
        var controller = new AdresseController(utilisationAdresse);

        controller.rechercheAdresse("adresse");
        verify(utilisationAdresse).chercherAdresse("adresse");
    }
}
