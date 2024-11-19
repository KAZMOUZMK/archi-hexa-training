package com.enslipchaussettes.api.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Panier {
    public  final UUID uuid;
    private List<Article> articles = new ArrayList<>();

    public Panier(){
       this.uuid =  UUID.randomUUID();
   }

    public void addReference(String reference) {
       this.addQuantiteParReference(1, reference);
    }

    @Deprecated
    public List<String> showPanier() {
        return articles.stream().map(a -> a.getReference()).toList();
    }

    public void deleteRef(String ref) {
        this.articles = this.articles.stream().filter(a -> !a.getReference().equals(ref)).toList();
    }

    public void addQuantiteParReference(int quantite, String reference) {
        int indexArticle = recupererIndexArticle(reference);
        if (indexArticle > -1) {
            articles.set(indexArticle, articles.get(indexArticle).ajoutQuantite2(quantite));
        } else {
            articles.add(new Article(reference, quantite));
        }
    }

    public List<Article> showPanierAvecQuantite() {
        return articles.stream().map(article -> new Article(article.getReference(), article.getQuantite())).toList();
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

    public void incrementerQuantite(String reference) {
        int indexArticle = recupererIndexArticle(reference);
        if(indexArticle > 0) {
            articles.set(indexArticle, articles.get(indexArticle).incrementeQuantite2());
        } else {
            addQuantiteParReference(1, reference);
        }
    }

    private Optional<Article> recupererArticle(String reference) {
        Optional<Article> articleOptional = this.articles.stream().filter(article -> article.getReference().equals(reference)).findFirst();
        return articleOptional;
    }

    public void decrementerQuantite(String reference) {
        int indexArticle = recupererIndexArticle(reference);

        if(indexArticle > -1){
            var newArticle = articles.get(indexArticle).decrementeQuantite2();
            if (!newArticle.estZero()) {
                articles.set(indexArticle, newArticle);
            } else {
                deleteRef(reference);
            }
        }
    }

    public void addProduit(Produit produit) {
        this.addQuantiteParProduit(1, produit);
    }

    public void addQuantiteParProduit(int quantite, Produit produit) {
        int indexArticle = recupererIndexArticle(produit.getReference());
        if (indexArticle > -1) {
            articles.set(indexArticle, articles.get(indexArticle).ajoutQuantite2(quantite));
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
            articles.set(indexArticle, articles.get(indexArticle).incrementeQuantite2());
        } else {
            addQuantiteParProduit(1, produit);
        }
    }

    public void decrementerQuantite(Produit produit) {
        int indexArticle = recupererIndexArticle(produit.getReference());

        if(indexArticle > -1){
            var newArticle = articles.get(indexArticle).decrementeQuantite2();
            if (!newArticle.estZero()) {
                articles.set(indexArticle, newArticle);
            } else {
                deleteProduit(produit);
            }
        }
    }
}
