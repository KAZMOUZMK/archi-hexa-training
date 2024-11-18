package com.enslipchaussettes.api.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PanierDoit {

    Panier panier;

    @BeforeEach
    void initPanier() {
        panier = new Panier();
    }


    @Test
    public void pouvoir_ajouter_une_reference() {
        panier.addReference("ref1");
        var actualContenu = panier.showPanier();

        assertNotNull(panier.uuid);
        assertEquals(actualContenu.getFirst(), "ref1");
    }

    @Test
    public void pouvoir_supprimer_une_reference() {
        panier.addReference("ref1");

        panier.deleteRef("ref1");
        var nouveauContenu = panier.showPanier();

        assertEquals(0, nouveauContenu.size());

    }

    @Test
    public void pouvoir_afficher_le_contenu_du_panier() {
        panier.addReference("ref1");
        panier.addReference("ref2");

        var contenu = panier.showPanier();

        assertTrue(contenu.contains("ref1"));
        assertTrue(contenu.contains("ref2"));

    }

    @Test
    public void pouvoir_ajouter_une_quantite_arbitraire_de_reference() {
        int quantite = 3;
        String reference = "ref2";

        panier.addQuantiteParReference(quantite, reference);
        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals("ref2", articles.getFirst().getReference());
        assertEquals(3, articles.getFirst().getQuantite());
    }

    @Test
    public void pouvoir_incrementer_quantite_dune_reference() {
        String reference = "ref1";

        panier.addQuantiteParReference(2, reference);
        panier.incrementerQuantite(reference);

        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals(3, articles.getFirst().getQuantite());


    }

    @Test
    public void pouvoir_decrementer_quantite_dune_reference() {
        String reference = "ref1";

        panier.addQuantiteParReference(2, reference);
        panier.decrementerQuantite(reference);

        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals(1, articles.getFirst().getQuantite());
    }

    @Test
    public void pouvoir_aggréger_les_quantites() {
        String reference = "ref1";

        panier.addQuantiteParReference(2, reference);
        panier.addQuantiteParReference(3, reference);

        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals(5, articles.getFirst().getQuantite());
    }

}
