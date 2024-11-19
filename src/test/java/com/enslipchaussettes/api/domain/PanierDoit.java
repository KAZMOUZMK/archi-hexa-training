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
    public void pouvoir_ajouter_un_produit() {
        panier.addProduit(new Produit("ref1", 20));
        var actualContenu = panier.showPanier();

        assertNotNull(panier.uuid);
        assertEquals("ref1", actualContenu.getFirst());
    }

    @Test
    public void pouvoir_supprimer_une_reference() {
        panier.addReference("ref1");

        panier.deleteRef("ref1");
        var nouveauContenu = panier.showPanier();

        assertEquals(0, nouveauContenu.size());

    }

    @Test
    public void pouvoir_supprimer_un_produit() {
        Produit produit = new Produit("ref1", 10);
        panier.addProduit(produit);

        panier.deleteProduit(produit);
        var nouveauContenu = panier.showPanier();

        assertEquals(0, nouveauContenu.size());

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
    public void pouvoir_ajouter_une_quantite_arbitraire_de_produit() {
        int quantite = 3;
        Produit produit = new Produit("ref2",0);
        panier.addQuantiteParProduit(quantite, produit);
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
    public void pouvoir_incrementer_quantite_dun_produit() {
        Produit produit = new Produit("ref2",0);
        panier.addQuantiteParProduit(2, produit);
        panier.incrementerQuantite(produit);

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
    public void pouvoir_decrementer_quantite_dun_produit() {
        Produit produit = new Produit("ref1",0);
        panier.addQuantiteParProduit(2, produit);
        panier.decrementerQuantite(produit);

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

    @Test
    public void pouvoir_aggréger_les_quantites_produits() {
        Produit produit = new Produit("ref1",0);

        panier.addQuantiteParProduit(2, produit);
        panier.addQuantiteParProduit(3, produit);

        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals(5, articles.getFirst().getQuantite());
    }

    @Test
    public void increment_ref_not_in_panier(){
        String reference = "ref1";
        panier.incrementerQuantite(reference);

        assertEquals(1, panier.showPanierAvecQuantite().getFirst().getQuantite());
    }

    @Test
    public void increment_produit_not_in_panier(){
        Produit produit = new Produit("ref1",0);
        panier.incrementerQuantite(produit);

        assertEquals(1, panier.showPanierAvecQuantite().getFirst().getQuantite());
    }

    @Test
    public void deincrement_ref_in_panier(){
        String reference = "ref1";
        panier.addQuantiteParReference(1, reference);
        panier.decrementerQuantite(reference);

        assertEquals(panier.showPanierAvecQuantite().size(), 0);
    }

    @Test
    public void deincrement_produit_in_panier(){
        Produit produit = new Produit("ref1",0);
        panier.addQuantiteParProduit(1, produit);
        panier.decrementerQuantite(produit);

        assertEquals(panier.showPanierAvecQuantite().size(), 0);
    }

    @Test
    public void deincrement_une_ref_inexistance_panier(){
        String reference = "ref1";
        panier.decrementerQuantite(reference);

        assertEquals(panier.showPanierAvecQuantite().size(), 0);
    }

    @Test
    public void deincrement_une_produit_inexistance_panier(){
        Produit produit = new Produit("ref1",0);
        panier.decrementerQuantite(produit);

        assertEquals(panier.showPanierAvecQuantite().size(), 0);
    }

}
