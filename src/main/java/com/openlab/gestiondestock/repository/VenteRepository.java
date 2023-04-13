package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.LigneCommandeClient;
import com.openlab.gestiondestock.model.LigneVente;
import com.openlab.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VenteRepository extends JpaRepository<Ventes, Integer> {
    Optional<Ventes> findByCode(String code);
    List<LigneVente> findAllByArticle(Integer idVente);
}
