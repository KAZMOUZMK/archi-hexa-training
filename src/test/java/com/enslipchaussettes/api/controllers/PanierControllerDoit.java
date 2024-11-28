package com.enslipchaussettes.api.controllers;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.controllers.panier.PanierController;
import com.enslipchaussettes.api.domain.panier.UtilisationPanier;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}
