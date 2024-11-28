package com.enslipchaussettes.api.domain.envoi;

import com.enslipchaussettes.api.domain.panier.Panier;

import java.util.UUID;

public class Envoi {


    private final String transporteur;
    private Etat etat;
    private String adresse;
    private String numeroSuivi;
    private Panier panier;
    private UUID uuid;

    public Envoi(Panier panier) {
        this.transporteur = "TransportCheap";
        this.etat = Etat.CONSTITUE;
        this.adresse = panier.getAdresse().rue();
        this.panier = panier;
        this.uuid = UUID.randomUUID();
    }


    public String getTransporteur() {
        return transporteur;
    }

    public String getNumeroSuivi() {
        return this.numeroSuivi;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void expedie(String numSuivi) {
        this.numeroSuivi = numSuivi;
        this.etat = Etat.EXPEDIE;
        this.panier.envoyer();
    }

    public UUID getId() {
        return this.uuid;
    }
}
