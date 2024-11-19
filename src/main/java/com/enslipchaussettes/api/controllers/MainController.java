package com.enslipchaussettes.api.controllers;

import com.enslipchaussettes.api.domain.Panier;
import com.enslipchaussettes.api.domain.PanierRep;
import com.enslipchaussettes.api.domain.UtilisationPanier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class MainController {

	@Autowired
	private UtilisationPanier utilisationPanier;

	@GetMapping("/")
	public ResponseEntity<String> index() {
		return new ResponseEntity<>("Greetings from Spring Boot!", HttpStatus.OK);
	}

	@PostMapping("/panier")
	public ResponseEntity<String> creerPanier() {
		UUID uuid = utilisationPanier.initPanier();
		return new ResponseEntity<>(uuid.toString(), HttpStatus.OK);
	}

	@GetMapping("/panier/{id}")
	public ResponseEntity<PanierResponse> getPanierByID(@PathVariable String id) {
		List<String> list = utilisationPanier.showReference(UUID.fromString(id));
		PanierResponse panierResponse = new PanierResponse(list);
		return new ResponseEntity<>(panierResponse, HttpStatus.OK);
	}


	@PutMapping("/panier/{id}")
	public ResponseEntity<String> savePanier(@PathVariable String id, @RequestBody PanierRequest panierRequest) {
		utilisationPanier.ajoutReference(UUID.fromString(id), panierRequest.getSku());
		return new ResponseEntity<>("",HttpStatus.OK);
	}
}

