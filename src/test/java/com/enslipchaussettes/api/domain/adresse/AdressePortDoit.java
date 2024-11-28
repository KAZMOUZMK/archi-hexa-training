package com.enslipchaussettes.api.domain.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.controllers.RechercheDetailAdresseResponse;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdressePortDoit {
    @Test
    public void doit_utiliser_le_repository_pour_chercher_une_adresse() {
        AdresseRepository repo = mock(AdresseRepository.class);

        AdressePort port = new AdressePort(repo);
        String adresse = "adresse";
        var expected = List.of(new RechercheAdresseResponse("adresseId", "xxx rue truc"));

        when(repo.rechercheAdresse("adresse")).thenReturn(expected);
        var adresseReponse = port.chercherAdresse(adresse);
        verify(repo).rechercheAdresse(adresse);
        assertEquals(expected, adresseReponse);
    }
    @Test
    public void doit_utiliser_le_repository_pour_chercher_detail_adresse() {
        AdresseRepository repo = mock(AdresseRepository.class);

        AdressePort port = new AdressePort(repo);
        String placeID = "placeId";
        var expected = new RechercheDetailAdresseResponse("street", "12345", "city", "france");

        when(repo.recupererDetail("placeId")).thenReturn(expected);
        var adresseDetailReponse = port.recupererDetail(placeID);
        verify(repo).recupererDetail("placeId");
        assertEquals(expected, adresseDetailReponse);
    }
}
