package com.enslipchaussettes.api.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class PanierPortDoit {
    @Test
    public void sauver_un_panier_initialise() {
        Catalogue catalogue = mock(Catalogue.class);
        PanierRep panierRep = mock(PanierRep.class);
        PanierPort utilisationPanier  = new PanierPort(panierRep, catalogue);

        UUID panierId = utilisationPanier.initPanier();
        verify(panierRep).savePanier(argThat(panier -> panier.uuid.equals(panierId)));
    }

    @Test
    public void ajouter_une_reference() {
        Catalogue catalogue = mock(Catalogue.class);
        PanierRep panierRep = mock(PanierRep.class);

        Panier panier = new Panier();
        when(panierRep.getPanier(any())).thenReturn(panier);
        Produit produit = new Produit("slip-noir", 10);
        when(catalogue.getProduit("slip-noir")).thenReturn(produit);
        PanierPort utilisationPanier  = new PanierPort(panierRep, catalogue);
        utilisationPanier.ajoutReference(panier.uuid, "slip-noir");
        verify(panierRep).savePanier(argThat(p -> p.uuid.equals(panier.uuid)));
        verify(catalogue).getProduit("slip-noir");
    }

    @Test
    public void supprimer_une_reference() {
        Catalogue catalogue = mock(Catalogue.class);
        PanierRep panierRep = mock(PanierRep.class);

        Panier panier = new Panier();
        when(panierRep.getPanier(any())).thenReturn(panier);
        Produit produit = new Produit("slip-noir", 10);
        when(catalogue.getProduit("slip-noir")).thenReturn(produit);
        PanierPort utilisationPanier  = new PanierPort(panierRep, catalogue);
        utilisationPanier.ajoutReference(panier.uuid, "slip-noir");
        utilisationPanier.deleteReference(panier.uuid, "slip-noir");
        verify(panierRep, times(2)).savePanier(argThat(p -> p.uuid.equals(panier.uuid)));
        verify(catalogue, times(2)).getProduit("slip-noir");
    }
}
