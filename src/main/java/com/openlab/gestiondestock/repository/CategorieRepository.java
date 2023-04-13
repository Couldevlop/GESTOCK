package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findByCode(String code);

}
