package com.enslipchaussettes.api.domain;

import com.enslipchaussettes.api.domain.panier.Article;
import com.enslipchaussettes.api.domain.panier.Panier;
import com.enslipchaussettes.api.domain.produit.Produit;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PanierSteps {

    private Panier panier;

    @Given("un panier")
    public void initPanier() {
        panier = new Panier();
    }

    @When("on ajoute une reference $reference")
    public void ajoutReference(String reference) {

        panier.addProduit(new Produit(reference, 10));
    }

    @Then("la première référence du panier doit être $reference")
    public void laReferenceDoitEtre(String reference) {
        List<Article> articles = panier.showPanierAvecQuantite();
        assertEquals(articles.getFirst().getReference(), reference);
    }
}
