package com.enslipchaussettes.api.controllers.panier;

import com.enslipchaussettes.api.domain.panier.Adresse;

import java.util.List;

public record PanierResponse(List<String> articles, AdresseEng address) {

}
