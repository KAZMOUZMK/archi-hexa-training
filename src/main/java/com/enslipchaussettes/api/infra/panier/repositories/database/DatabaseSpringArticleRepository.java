package com.enslipchaussettes.api.infra.panier.repositories.database;

import com.enslipchaussettes.api.infra.dao.ArticleDao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DatabaseSpringArticleRepository extends JpaRepository<ArticleDao, Long> {

}
