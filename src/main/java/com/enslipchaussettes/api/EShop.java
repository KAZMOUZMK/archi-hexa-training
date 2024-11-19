package com.enslipchaussettes.api;

import com.enslipchaussettes.api.database.DatabaseSpringArticleRepository;
import com.enslipchaussettes.api.database.DatabaseSpringPanierRepository;
import com.enslipchaussettes.api.domain.PanierPort;
import com.enslipchaussettes.api.domain.PanierRep;
import com.enslipchaussettes.api.domain.UtilisationPanier;
import com.enslipchaussettes.api.infra.CatalogueEnMemoire;
import com.enslipchaussettes.api.infra.PanierRepDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EShop {

	@Autowired
	private DatabaseSpringPanierRepository repositorypanier;

	@Autowired
	private DatabaseSpringArticleRepository repositoryArticle;

	public static void main(String[] args) {
		SpringApplication.run(EShop.class, args);
	}

	@Bean
	public UtilisationPanier utiliserPanier() {
		var catalogue = new CatalogueEnMemoire();
		var panierRepository = new PanierRepDatabaseImpl(repositorypanier, repositoryArticle, catalogue);
		return new PanierPort(panierRepository, catalogue);
	}

}
