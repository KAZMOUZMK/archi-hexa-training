package com.enslipchaussettes.api.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Panier {
    public  final UUID uuid;
    private List<String> listReference = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();

    public Panier(){
       this.uuid =  UUID.randomUUID();
   }

    public void addReference(String reference) {
       this.listReference.add(reference);
    }

    public List<String> showPanier() {
        return listReference;
    }

    public void deleteRef(String ref) {
        this.listReference = this.listReference.stream().filter(l-> !l.equals(ref)).toList();
    }

    public int recupererQuantite(String reference) {
        return 0;
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
        articleOptional.get().incrementeQuantite();

    }

    private Optional<Article> recupererArticle(String reference) {
        Optional<Article> articleOptional = this.articles.stream().filter(article -> article.getReference().equals(reference)).findFirst();
        return articleOptional;
    }

    public void decrementerQuantite(String reference) {
        Optional<Article> articleOptional = recupererArticle(reference);
        articleOptional.get().decrementeQuantite();
    }
}
