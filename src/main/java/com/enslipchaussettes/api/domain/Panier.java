package com.enslipchaussettes.api.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Panier {
    public  final UUID uuid;
    private List<Article> articles = new ArrayList<>();

    public Panier(){
       this.uuid =  UUID.randomUUID();
   }

    @Deprecated
    public List<String> showPanier() {
        return articles.stream().map(a -> a.getReference()).toList();
    }

    public List<Article> showPanierAvecQuantite() {
        return articles.stream().map(article -> article).toList(); // new Article(article.getReference(), article.getQuantite())).toList();
    }

    private int recupererIndexArticle(String reference) {
        int i = -1;
        for (Article a :this.articles) {
            i++;
            if (a.getReference().equals(reference)) {
                return i;
            }
        }
        return -1;

    }

    public void addProduit(Produit produit) {
        this.addQuantiteParProduit(1, produit);
    }

    public void addQuantiteParProduit(int quantite, Produit produit) {
        int indexArticle = recupererIndexArticle(produit.getReference());
        if (indexArticle > -1) {
            articles.set(indexArticle, articles.get(indexArticle).ajoutQuantite(quantite));
        } else {
            articles.add(new Article(produit, quantite));
        }
    }


    public void deleteProduit(Produit produit) {
        this.articles = this.articles.stream().filter(a -> !a.estProduit(produit)).toList();
    }

    public void incrementerQuantite(Produit produit) {
        int indexArticle = recupererIndexArticle(produit.getReference());
        if(indexArticle > 0) {
            articles.set(indexArticle, articles.get(indexArticle).incrementeQuantite());
        } else {
            addQuantiteParProduit(1, produit);
        }
    }

    public void decrementerQuantite(Produit produit) {
        int indexArticle = recupererIndexArticle(produit.getReference());

        if(indexArticle > -1){
            var newArticle = articles.get(indexArticle).decrementeQuantite();
            if (!newArticle.estZero()) {
                articles.set(indexArticle, newArticle);
            } else {
                deleteProduit(produit);
            }
        }
    }
}
