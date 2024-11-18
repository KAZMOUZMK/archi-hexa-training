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
        Optional<Article> articleOptionl = recupererArticle(reference);

        if(articleOptionl.isPresent()) {
            articleOptionl.get().ajoutQuantite(quantite);
        } else {
            articles.add(new Article(reference, quantite));
        }
    }

    public List<Article> showPanierAvecQuantite() {
        return articles.stream().map(article -> new Article(article.getReference(), article.getQuantite())).toList();
    }

    public void incrementerQuantite(String reference) {
        Optional<Article> articleOptional = recupererArticle(reference);
        if(articleOptional.isPresent()) {
            articleOptional.get().incrementeQuantite();
        } else {
            addQuantiteParReference(1, reference);
        }
    }

    private Optional<Article> recupererArticle(String reference) {
        Optional<Article> articleOptional = this.articles.stream().filter(article -> article.getReference().equals(reference)).findFirst();
        return articleOptional;
    }

    public void decrementerQuantite(String reference) {
        Optional<Article> articleOptional = recupererArticle(reference);
        if(articleOptional.isPresent()){
            articleOptional.get().decrementeQuantite();
            if(articleOptional.get().getQuantite() == 0){
                deleteRef(reference);
            }
        }
    }
}
