package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.security.dto.ExtendedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Boolean existsByEmail(String email);
    Optional<Utilisateur>findByEmail(String email);

    Optional<Utilisateur> findByUsername(String username);

}
