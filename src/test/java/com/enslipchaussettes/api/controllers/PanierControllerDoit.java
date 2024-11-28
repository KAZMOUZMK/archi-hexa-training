package com.enslipchaussettes.api.controllers;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.controllers.panier.PanierController;
import com.enslipchaussettes.api.domain.panier.UtilisationPanier;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PanierControllerDoit {
    @Test
    public void permettre_d_ajouter_une_adresse_sur_un_panier() {
        UtilisationPanier utilisationPanier = mock(UtilisationPanier.class);
        PanierController controller = new PanierController(utilisationPanier);
        AdresseRequest adresseRequest = new AdresseRequest("foobar", "10 rue truc", "75001", "Paris", "France");
        var uuid = UUID.randomUUID();
        controller.ajouterAdresse(uuid.toString(), adresseRequest);
        verify(utilisationPanier).ajouterAdresse(uuid, adresseRequest);
    }

    @Test
    public void appeler_use_case_pour_presenter_panier() {
        UtilisationPanier utilisationPanier = mock(UtilisationPanier.class);
        PanierController controller = new PanierController(utilisationPanier);
        UUID panierId = UUID.randomUUID();
        controller.getPanierByID(panierId.toString());
        verify(utilisationPanier).presenterPanier(any(), any());
    }

    @Test
    public void appeler_use_case_pour_valider_un_panier() {
        UtilisationPanier utilisationPanier = mock(UtilisationPanier.class);
        PanierController controller = new PanierController(utilisationPanier);
        var envoiId = UUID.randomUUID();
        UUID panierId = UUID.randomUUID();
        when(utilisationPanier.validerPanier(panierId)).thenReturn(envoiId);
        var resp = controller.validerPanier(panierId.toString());
        verify(utilisationPanier).validerPanier(panierId);
        assertEquals(envoiId.toString(), resp.getBody().toString());
    }
}
