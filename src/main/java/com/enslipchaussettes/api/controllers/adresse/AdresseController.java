package com.enslipchaussettes.api.controllers.adresse;

import com.enslipchaussettes.api.controllers.RechercheAdresseResponse;
import com.enslipchaussettes.api.domain.adresse.UtilisationAdresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdresseController {
    @Autowired
    private UtilisationAdresse utilisationAdresse;
    public AdresseController(UtilisationAdresse utilisationAdresse) {
        this.utilisationAdresse = utilisationAdresse;
    }

    @GetMapping("/adresse")
    public ResponseEntity<List<RechercheAdresseResponse>> rechercheAdresse(@RequestParam String search) {
        var response = utilisationAdresse.chercherAdresse(search);
        return ResponseEntity.ok(response);
    }
}
