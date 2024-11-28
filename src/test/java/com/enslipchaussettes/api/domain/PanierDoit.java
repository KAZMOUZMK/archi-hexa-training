package com.enslipchaussettes.api.domain;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.domain.panier.Article;
import com.enslipchaussettes.api.domain.panier.Panier;
import com.enslipchaussettes.api.domain.produit.Produit;
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
    public void pouvoir_ajouter_un_produit() {
        panier.addProduit(new Produit("ref1", 20));
        var actualContenu = panier.showPanier();

        assertNotNull(panier.uuid);
        assertEquals("ref1", actualContenu.getFirst());
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
    public void pouvoir_ajouter_une_quantite_arbitraire_de_produit() {
        int quantite = 3;
        Produit produit = new Produit("ref2",0);
        panier.addQuantiteParProduit(quantite, produit);
        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals("ref2", articles.getFirst().getReference());
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
    public void pouvoir_decrementer_quantite_dun_produit() {
        Produit produit = new Produit("ref1",0);
        panier.addQuantiteParProduit(2, produit);
        panier.decrementerQuantite(produit);

        List<Article> articles = panier.showPanierAvecQuantite();

        assertEquals(1, articles.getFirst().getQuantite());
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
    public void increment_produit_not_in_panier(){
        Produit produit = new Produit("ref1",0);
        panier.incrementerQuantite(produit);

        assertEquals(1, panier.showPanierAvecQuantite().getFirst().getQuantite());
    }

    @Test
    public void deincrement_produit_in_panier(){
        Produit produit = new Produit("ref1",0);
        panier.addQuantiteParProduit(1, produit);
        panier.decrementerQuantite(produit);

        assertEquals(panier.showPanierAvecQuantite().size(), 0);
    }

    @Test
    public void deincrement_une_produit_inexistance_panier(){
        Produit produit = new Produit("ref1",0);
        panier.decrementerQuantite(produit);

        assertEquals(panier.showPanierAvecQuantite().size(), 0);
    }

    @Test
    public void permettre_d_ajouter_une_adresse() {
        var panier = new Panier();
        var   adresseRequest = new AdresseRequest("foobar", "10 rue truc", "75001", "Paris", "France");
        panier.ajouterAdresse(adresseRequest);
        var actual = panier.getAdresse();
        assertEquals(adresseRequest.nom(), actual.nom());
        assertEquals(adresseRequest.rue(), actual.rue());
        assertEquals(adresseRequest.codePostal(), actual.codePostal());
        assertEquals(adresseRequest.ville(), actual.ville());
        assertEquals(adresseRequest.pays(), actual.pays());
    }

}
