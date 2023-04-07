package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findByNom(String nom);
}
