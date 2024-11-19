package com.enslipchaussettes.api.infra.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Article")
public class ArticleDao {

    public ArticleDao() {
    }

    public ArticleDao(String reference, int quantite, PanierDao panierDao) {
        this.reference = reference;
        this.quantite = quantite;
        this.panier = panierDao;
    }


    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="panier_id", nullable=false)
    private PanierDao panier;

    public long getId() {
        return id;

    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    private String reference;

    private int quantite;

    public PanierDao getPanier() {
        return panier;
    }

    public void setPanier(PanierDao panier) {
        this.panier = panier;
    }



}
