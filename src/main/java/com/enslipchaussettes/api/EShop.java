package com.enslipchaussettes.api;

import com.enslipchaussettes.api.domain.adresse.AdressePort;
import com.enslipchaussettes.api.domain.adresse.UtilisationAdresse;
import com.enslipchaussettes.api.domain.envoi.EnvoiPort;
import com.enslipchaussettes.api.domain.envoi.EnvoiRep;
import com.enslipchaussettes.api.domain.envoi.UtilisationEnvoi;
import com.enslipchaussettes.api.domain.panier.PanierRep;
import com.enslipchaussettes.api.infra.adresse.GoogleApiAdresse;
import com.enslipchaussettes.api.infra.envoi.EnvoiRepositoryHybride;
import com.enslipchaussettes.api.infra.panier.repositories.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.infra.panier.repositories.database.DatabaseSpringPanierRepository;
import com.enslipchaussettes.api.domain.panier.PanierPort;
import com.enslipchaussettes.api.domain.panier.UtilisationPanier;
import com.enslipchaussettes.api.infra.produit.CatalogueEnMemoire;
import com.enslipchaussettes.api.infra.panier.repositories.PanierRepDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EShop {

	@Autowired
	private DatabaseSpringPanierRepository databaseSpringPanierRepository;

	@Autowired
	private DatabaseSpringArticleRepository databaseSpringArticleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EShop.class, args);
	}

	@Bean
	public UtilisationPanier utiliserPanier() {
		var catalogue = new CatalogueEnMemoire();
		var panierRepository = new PanierRepDatabaseImpl(databaseSpringPanierRepository, databaseSpringArticleRepository, catalogue);
		var envoiRepository = EnvoiRepositoryHybride.getInstance(panierRepository);
		return new PanierPort(panierRepository, catalogue, envoiRepository);
	}

	@Bean
	public UtilisationAdresse utilisationAdresse() {
		GoogleApiAdresse apiAdresse = new GoogleApiAdresse();
		return new AdressePort(apiAdresse);
	}

	@Bean
	public UtilisationEnvoi utilisationEnvoi() {
		var panierRepository = new PanierRepDatabaseImpl(databaseSpringPanierRepository, databaseSpringArticleRepository, new CatalogueEnMemoire());
		var envoiRepository = EnvoiRepositoryHybride.getInstance(panierRepository);
		return new EnvoiPort(envoiRepository);
	}

}
