package com.enslipchaussettes.api.domain.envoi;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.domain.panier.EtatPanier;
import com.enslipchaussettes.api.domain.panier.Panier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnvoiDoit {

    @Test
    void pouvoir_generer_un_envoi() {
        Panier panier = new Panier();
        AdresseRequest adresseRequest = new AdresseRequest("maison","7 rue de Paris", "93100", "Montreuil", "France");
        panier.ajouterAdresse(adresseRequest);

        Envoi envoi = new Envoi(panier);
        assertEquals("TransportCheap", envoi.getTransporteur());
        assertNull(envoi.getNumeroSuivi());
        assertEquals(Etat.CONSTITUE, envoi.getEtat());
        assertEquals("7 rue de Paris", envoi.getAdresse());

    }

    @Test
    void avoir_numero_suivi_quand_expedie() {
        Panier panier = new Panier();
        AdresseRequest adresseRequest = new AdresseRequest("maison","7 rue de Paris", "93100", "Montreuil", "France");
        panier.ajouterAdresse(adresseRequest);

        Envoi envoi = new Envoi(panier);
        envoi.expedie("NumSuivi");

        assertEquals(EtatPanier.ENVOYE, panier.getEtat());
        assertEquals("NumSuivi",envoi.getNumeroSuivi());
        assertEquals(Etat.EXPEDIE, envoi.getEtat());
    }





}
