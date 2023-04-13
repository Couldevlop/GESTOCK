package com.openlab.gestiondestock.repository;

import com.openlab.gestiondestock.model.CommandeClient;
import com.openlab.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient>findCommandeClientByCode(String code);
    List<LigneCommandeClient> findByArticleId(Integer idArticle);
    List<CommandeClient> findByAllClientId(Integer id);
    List<LigneCommandeClient> findByAllCommandClienId(Integer id);


}
