package com.enslipchaussettes.api.infra.panier.repositories.database;

import java.util.Optional;
import java.util.UUID;

import com.enslipchaussettes.api.infra.dao.PanierDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseSpringPanierRepository extends JpaRepository<PanierDao, UUID> {
    Optional<PanierDao> findById(UUID id);
}
