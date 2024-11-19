package com.enslipchaussettes.api.controllers.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.domain.adresse.UtilisationAdresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AdresseController {
    @Autowired
    private UtilisationAdresse utilisationAdresse;
    public AdresseController(UtilisationAdresse utilisationAdresse) {
        this.utilisationAdresse = utilisationAdresse;
    }

    @GetMapping("/address")
    public ResponseEntity<RechercheAdresseResponse> rechercheAdresse(@RequestParam String adresse) {
        RechercheAdresseResponse response = utilisationAdresse.chercherAdresse(adresse);
        return ResponseEntity.ok(response);
    }
}
