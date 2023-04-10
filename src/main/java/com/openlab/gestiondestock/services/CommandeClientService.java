package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto commandeClientDto);
    CommandeClientDto updateEtaCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLignCmd, BigDecimal quantite);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCmd, Integer idArticle);
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCmd);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();

    List<LigneCommandeClientDto>findAllLigneCommandeClientBycommandeClientId(Integer idCommande);
    void delete(Integer id);
}
