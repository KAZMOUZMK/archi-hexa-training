package com.enslipchaussettes.api.infra.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Panier")
public class PanierDao {

    @Id
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public String nom;
    public String rue;
    public String codePostal;
    public String ville;
    public String Pays;

    @OneToMany(mappedBy="panier",fetch = FetchType.EAGER)
    private List<ArticleDao> articles = new LinkedList<>();

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<ArticleDao> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDao> articles) {
        this.articles = articles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }
}
