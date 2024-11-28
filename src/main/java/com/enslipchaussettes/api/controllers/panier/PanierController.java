package com.enslipchaussettes.api.controllers.panier;

import com.enslipchaussettes.api.domain.panier.UtilisationPanier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PanierController {

	@Autowired
	private UtilisationPanier utilisationPanier;

	public PanierController(UtilisationPanier utilisationPanier) {
		this.utilisationPanier=utilisationPanier;
	}

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
		PanierJsonPresenter panierPresenter = new PanierJsonPresenter();
		utilisationPanier.presenterPanier(UUID.fromString(id), panierPresenter);
		return new ResponseEntity<>(panierPresenter.genererReponse(), HttpStatus.OK);
	}

	@PutMapping("/panier/{id}")
	public ResponseEntity<String> savePanier(@PathVariable String id, @RequestBody PanierRequest panierRequest) {
		utilisationPanier.ajoutReference(UUID.fromString(id), panierRequest.sku());
		return ResponseEntity.ok("");
	}
	@PutMapping("/panier/{id}/adresse")
	public ResponseEntity<Void> ajouterAdresse(@PathVariable  String id, @RequestBody AdresseRequest adresseRequest) {
		utilisationPanier.ajouterAdresse(UUID.fromString(id), adresseRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

