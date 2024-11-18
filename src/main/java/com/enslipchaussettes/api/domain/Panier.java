package com.enslipchaussettes.api.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Panier {
    public  final UUID uuid;
    private List<String> listReference = new ArrayList<>();

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
}
