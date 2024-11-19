package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdressePortDoit {
    @Test
    public void doit_utiliser_le_repository_pour_chercher_une_adresse() {
        AdresseRepository repo = mock(AdresseRepository.class);

        AdressePort port = new AdressePort(repo);
        String adresse = "adresse";
        var expected = new RechercheAdresseResponse("adresseId", "xxx rue truc");
        when(repo.rechercheAdresse("adresse")).thenReturn(expected);
        var adresseReponse = port.chercherAdresse(adresse);
        verify(repo).rechercheAdresse(adresse);
        assertEquals(expected, adresseReponse);
    }
}
