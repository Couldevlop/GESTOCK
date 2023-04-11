package com.openlab.gestiondestock.services;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.model.dto.CommandeFournisseurDto;
import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeFournisseurDto;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournissuerService {
    CommandeFournisseurDto save(CommandeFournisseurDto fournisseurDto);
    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto findByCode(String code);
    List<CommandeFournisseurDto> findAll();
    CommandeFournisseurDto updateEtaCommande(Integer idCommande, EtatCommande etatCommande);
    CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLignCmd, BigDecimal quantite);
    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFour);
    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCmd, Integer idArticle);
    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCmd);
    List<LigneCommandeFournisseurDto>findAllLigneCommandeFournisseurBycommandeFournisseurId(Integer idCommande);
    void delete(Integer id);
}
