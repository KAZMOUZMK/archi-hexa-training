package com.enslipchaussettes.api.domain.panier;

import com.enslipchaussettes.api.controllers.panier.AdresseRequest;
import com.enslipchaussettes.api.domain.envoi.Envoi;
import com.enslipchaussettes.api.domain.envoi.EnvoiRep;
import com.enslipchaussettes.api.domain.produit.Catalogue;

import java.util.List;
import java.util.UUID;

public class PanierPort implements UtilisationPanier {
    private  EnvoiRep envoiRep;
    private Catalogue catalogue = null;
    private final PanierRep panierRep;

    public PanierPort(PanierRep panierRep, Catalogue catalogue) {
        this.panierRep = panierRep;
        this.catalogue = catalogue;
    }

    public PanierPort(PanierRep panierRep, Catalogue catalogue, EnvoiRep envoiRep) {
        this.panierRep = panierRep;
        this.catalogue = catalogue;
        this.envoiRep = envoiRep;
    }

    @Override
    public UUID initPanier(){
        Panier panier = new Panier();
        panierRep.savePanier(panier);
        return panier.uuid;
    }

    @Override
    public void ajoutReference(UUID uuid, String reference){
        Panier  panier = panierRep.getPanier(uuid);
        var produit = catalogue.getProduit(reference);
        panier.addProduit(produit);
        panierRep.savePanier(panier);
    }

    @Override
    public List<String> showReference(UUID uuid){
        Panier  panier = panierRep.getPanier(uuid);
        return panier.showPanier();
    }

    @Override
    public void deleteReference(UUID uuid, String ref){
        Panier  panier = panierRep.getPanier(uuid);
        var produit = catalogue.getProduit(ref);
        panier.deleteProduit(produit);
        panierRep.savePanier(panier);
    }

    @Override
    public void showReference(UUID uuid, PanierPresenter panierPresenter) {
        Panier  panier = panierRep.getPanier(uuid);
        panierPresenter.ajouterContenu(panier.showPanier());

    }

    @Override
    public void ajouterAdresse(UUID panierId, AdresseRequest adresseRequest) {
        Panier  panier = panierRep.getPanier(panierId);
        panier.ajouterAdresse(adresseRequest);
        panierRep.savePanier(panier);
    }

    @Override
    public void presenterPanier(UUID panierId, PanierPresenter panierPresenter) {
        Panier  panier = panierRep.getPanier(panierId);
        var adresse = panier.getAdresse();
        panierPresenter.ajouterContenu(panier.showPanierAvecQuantite().stream().map(a -> String.format("%s - %d", a.getReference(), a.getQuantite())).toList());
        panierPresenter.ajouterAdresse(adresse);

    }

    @Override
    public UUID validerPanier(UUID uuid) {
        Panier panier = panierRep.getPanier(uuid);
        Envoi envoi = panier.validerPanier();
        envoiRep.saveEnvoi(envoi);
        return envoi.getId();
    }
}
