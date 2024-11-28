package com.enslipchaussettes.api.controllers.envoi;

import com.enslipchaussettes.api.domain.envoi.EnvoiPort;
import com.enslipchaussettes.api.domain.envoi.EnvoiRep;
import com.enslipchaussettes.api.domain.envoi.UtilisationEnvoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EnvoiController {
    private final UtilisationEnvoi utilisationEnvoi;

    public EnvoiController(UtilisationEnvoi utilisationEnvoi) {
        this.utilisationEnvoi = utilisationEnvoi;
    }

    @PutMapping("/envoi/{id}/expedier")
    public ResponseEntity<Void> expedierEnvoi(@PathVariable String id, @RequestBody ExpeditionRequest request) {
        this.utilisationEnvoi.expedierEnvoi(UUID.fromString(id), request.numSuivi());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/envoi/{id}")
    public ResponseEntity<EnvoiResponse> recupererEnvoi(@PathVariable String id) {
        // this.utilisationEnvoi.expedierEnvoi(UUID.fromString(id), request.numSuivi());
        var response = new EnvoiResponse("numSuivi");
        return ResponseEntity.ok(response);
    }

}
