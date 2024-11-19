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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<ArticleDao> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDao> articles) {
        this.articles = articles;
    }

    @OneToMany(mappedBy="panier",fetch = FetchType.EAGER)
    private List<ArticleDao> articles = new LinkedList<>();

    private String reference;
}
