package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepsitory extends JpaRepository<Ventes, Integer> {
    Optional<Ventes> findByCode(String code);
}
