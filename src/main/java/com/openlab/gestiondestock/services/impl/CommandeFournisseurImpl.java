package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.enums.SourceMvtStk;
import com.openlab.gestiondestock.enums.TypeMvtStk;
import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.*;
import com.openlab.gestiondestock.model.dto.*;
import com.openlab.gestiondestock.repository.*;
import com.openlab.gestiondestock.services.CommandeFournissuerService;
import com.openlab.gestiondestock.services.MvtStkService;
import com.openlab.gestiondestock.validator.ArticleValidator;
import com.openlab.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandeFournisseurImpl implements CommandeFournissuerService {
    private final CommandeFournisseurRepository commandeFournisseurRepository;

    private final ClientRepository clientRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;

    private final ArticleRepository articleRepository;

    private final MvtStkService mvtStkService;
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {

        // verifier la validation de l'objet CommandeFournisseur -> Fournisseur -> Article
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("L'objet fourni est invalide");
            throw new InvalidEntityException("L'objet fourni est invalide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        // en cas de mofication de la commande client
        if(dto.getId() != null && dto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est validée", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }

        // verifier si le FOURNISSEUR de la COMMANDEFOURNISSEUR existe en BD
        fournisseurRepository.findById(dto.getFournisseur().getId()).map(FournisseurDto::fromEntity).orElseThrow(()->{
            throw new InvalidEntityException("Aucun fournisseur n'est associé à cette commande", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        });

        // Verifier s'il ya des lignes de commandes ->article dans la BD
        List<String> errorsMsg = new ArrayList<>();
        if(dto.getLigneCommandeFournisseur() != null){
            dto.getLigneCommandeFournisseur().forEach(ligneCmd ->{
                if(ligneCmd.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCmd.getId());
                    if(article.isEmpty()){
                        errorsMsg.add("l'article avec l'id" + ligneCmd.getArticle().getId() + " est introuvable dans la BD");
                    }
                }else {
                    errorsMsg.add("impossible d'ajouter un article null");
                }
            });
        }
        if(!errorsMsg.isEmpty()){
            log.warn("il n'a pas d'article dans cette ligne de commande fournisseur");
            throw  new InvalidEntityException("Plusieurs articles de cette ligne de commande client sont introuvable dans la BD", ErrorCodes.ARTICLE_NOT_VALID,errorsMsg);
        }


        //Ajouter la commande fournisseur a chaque ligne de commande
        CommandeFournisseur savedCmdFour = commandeFournisseurRepository.save(CommandeFournisseurDto.fromEntityDTO(dto));
        if(dto.getLigneCommandeFournisseur() != null){
            dto.getLigneCommandeFournisseur().forEach(cmdFour ->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.fromEntityDTO(cmdFour);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFour);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFour);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id == null){
            log.error("l'id fourni est null");
            return null;
        }
    return  commandeFournisseurRepository.findById(id).map(CommandeFournisseurDto::fromEntity).orElseThrow(()->{
             throw new EntityNotFoundException("la commande avec l'id " + id + "est introuvable",ErrorCodes.FOURNISSEUR_NOT_FOUND);
         });
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(code == null){
            log.error("le code fourni est null");
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code).map(CommandeFournisseurDto::fromEntity).orElseThrow(() ->{
            throw new EntityNotFoundException("La commande fournisseur avec le code " + code + " est introuvable", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        });
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream().map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CommandeFournisseurDto updateEtaCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);

        if(!StringUtils.hasLength(String.valueOf(etatCommande))){
            log.error("l'etat de la commande  est null");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }
        //Verifier si la commande n'est pas livré avant de faire la modification de l'etat
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        if(commandeFournisseurDto.getId() != null && commandeFournisseurDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est validée",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }
        commandeFournisseurDto.setEtatCommande(etatCommande);
        CommandeFournisseur savedCmdFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.fromEntityDTO(commandeFournisseurDto));

        //--- Mise à jour du stock si la commande est livrée
        if(commandeFournisseurDto.isCommandeLivree()){
            updateMvtStk(idCommande);
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLignCmd, BigDecimal quantite) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLignCmd);

        if(quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0){
            log.error("l'id de la ligne de commande  est null");
            throw  new InvalidOperationException("Impossible de modifier la quantité de la commande avec un id de ligne de commande null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if(commandeFournisseurDto.getId() != null && commandeFournisseurDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier la commande avec une quantité null ou 0",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }

        ligneCommandeFournisseurRepository.findById(idLignCmd).map(LigneCommandeFournisseurDto::fromEntity).orElseThrow(() ->{
            throw new EntityNotFoundException("La ligne de commande correspondant à l'id " + idLignCmd + " est introuvable",
                    ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
        });
        LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurRepository.findById(idLignCmd).get();
        ligneCommandeFournisseur.setQuantite(quantite);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFourn) {
        checkIdCommande(idCommande);
        if(idFourn == null){
            log.error("l'id du client  est null");
            throw  new InvalidOperationException("Impossible de modifier le client avec un id  null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<Fournisseur> four = fournisseurRepository.findById(idFourn);
        if(four.isEmpty()){
            throw new EntityNotFoundException("le client n'a pas été trouvé", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        commandeFournisseurDto.setFournisseur(FournisseurDto.fromEntity(four.get()));
        return CommandeFournisseurDto.fromEntity(commandeFournisseurRepository.save(CommandeFournisseurDto.fromEntityDTO(commandeFournisseurDto)));
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCmd, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCmd);
        checkIdArticle(idArticle,"nouveau");
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = findLigneCommandeClient(idCommande);
        Optional<Article> article = articleRepository.findById(idArticle);
        if(article.isEmpty()){
            throw new EntityNotFoundException("l'article n'a pas été trouvé avec l'id " + idArticle,
                    ErrorCodes.ARTICLE_NOT_FOUND);
        }

        List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(article.get()));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("l'article de la commande est invalide",
                    ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        LigneCommandeFournisseur ligneCommandeFournisseur1 = ligneCommandeFournisseur.get();
        ligneCommandeFournisseur1.setArticle(article.get());
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur1);
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCmd) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCmd);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        // verifier l'existance du client et informer l'utilisateur
        findLigneCommandeClient(idCommande);
        ligneCommandeFournisseurRepository.deleteById(idLigneCmd);
        return commandeFournisseurDto;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurBycommandeFournisseurId(Integer idCommande) {
        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande).stream().map(LigneCommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
      commandeFournisseurRepository.deleteById(id);
    }

    //***************** REFACTORING*********************

    /*
     * Verifier l'existance d'un article dans la commande
     * Actions importante avant toutes modifications
     */
    private void checkIdArticle(Integer idArticle, String msg) {
        if(idArticle == null){
            log.error("l'id du " + msg + "article  est null");
            throw  new InvalidOperationException("Impossible de modifier la commande avec un" + msg + " id Article null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }
    }

    /*
     * Methode pour verifier l'etat de la commande
     * Pour toutes actions de modifications, la commande ne doit pas avoir le statut de LIVREE
     */
    private CommandeFournisseurDto checkEtatCommande(Integer idCommande){
        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if(commandeFournisseurDto.getId() != null && commandeFournisseurDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier le client pour cette commande livrée",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }
        return commandeFournisseurDto;
    }


    /*
     * Verifier une commande existe
     * Action importante avant toutes modifications
     */

    private void checkIdCommande(Integer idCommande){
        if(idCommande == null){
            log.error("l'id est null");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande avec un id null",
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE);
        }
    }


    /*
     * verifier l'existance d'une ligne de commande associée à cette commande fournisseur
     * Action importante avant la modification
     */
    private void checkIdLigneCommande(Integer idLignCmd) {
        if(idLignCmd == null){
            log.error("l'id de la ligne de commande  est null");
            throw  new InvalidOperationException("Impossible de modifier la quantité de la commande avec un id de ligne de commande null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
    }

    private Optional<LigneCommandeFournisseur>findLigneCommandeClient(Integer idCommande){
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = ligneCommandeFournisseurRepository.findById(idCommande);
        if(ligneCommandeFournisseur.isEmpty()){
            throw new EntityNotFoundException("Aucune ligne de commande n'a été trouvé avec l'id " + idCommande,
                    ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }else {
            return ligneCommandeFournisseur;
        }

    }

    //-- Méthode appeller pour chaque mise à jour de stock(commande-clien, commende fournisseur ou vente
    private void updateMvtStk(Integer idCommande){
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);
        ligneCommandeFournisseurs.forEach(lig ->{
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .quantite(lig.getQuantite())
                    .idEntreprise(lig.getIdEntreprise())
                    .sourceMvtStk(SourceMvtStk.COMMANDE_FOURNISSEUR)
                    .id(lig.getId())
                    .typeMvtStk(TypeMvtStk.ENTREE)
                    .article(ArticleDto.fromEntity(lig.getArticle()))
                    .dateMvt(Instant.now()).build();
            mvtStkService.entreeStock(mvtStkDto);
        });


    }
}
