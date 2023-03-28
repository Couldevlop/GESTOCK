package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
