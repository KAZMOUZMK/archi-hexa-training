package com.enslipchaussettes.api.controllers.panier;

import com.enslipchaussettes.api.domain.panier.Adresse;
import com.enslipchaussettes.api.domain.panier.PanierPresenter;

import java.util.List;

public class PanierJsonPresenter implements PanierPresenter {
    private List<String> contenu = null;
    private Adresse adresse = null;

    public PanierResponse genererReponse() {

        AdresseEng adresseEng = null;
        if (adresse != null) {
            adresseEng = new AdresseEng(
                    adresse.nom(),
                    adresse.rue(),
                    adresse.codePostal(),
                    adresse.ville(),
                    adresse.pays()
                    );
        }

        return new PanierResponse(contenu, adresseEng);
    }

    @Override
    public void ajouterContenu(List<String> strings) {
        this.contenu = strings;
    }

    @Override
    public void ajouterAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
