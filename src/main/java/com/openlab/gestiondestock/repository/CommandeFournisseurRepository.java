package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.CommandeFournisseur;
import com.openlab.gestiondestock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur>findCommandeFournisseurByCode(String code);
    List<LigneCommandeFournisseur> findByArticleId(Integer idArticle);
    List<CommandeFournisseur>findByAllFournisseurId(Integer id);



}
