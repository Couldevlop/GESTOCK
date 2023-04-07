package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.enums.EtatCommande;
import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.CommandeClient;
import com.openlab.gestiondestock.model.LigneCommandeClient;
import com.openlab.gestiondestock.model.dto.ClientDto;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.repository.ArticleRepository;
import com.openlab.gestiondestock.repository.ClientRepository;
import com.openlab.gestiondestock.repository.CommandeClientRepository;
import com.openlab.gestiondestock.repository.LigneCommandeClientRepository;
import com.openlab.gestiondestock.services.CommandeClientService;
import com.openlab.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
    private final CommandeClientRepository commandeClientRepository;
    private final ClientRepository clientRepository;
    private  final ArticleRepository articleRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        // verifier la validation de l'objet CommandeClient -> Client -> Article

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if(!errors.isEmpty()){
            log.error("l'objet attendu est invalide");
            throw  new InvalidEntityException("L'objet attendu est invalide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        // en cas de mofication de la commande client
        if(commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est validée", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }

        // verifier si le CLIENT de la COMMANDECLIENT existe en BD
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if(!client.isPresent()){
            log.warn("la commande n'a pas de client existant dans la BD {}", commandeClientDto.getClient().getId() );
            throw new EntityNotFoundException("le client avec l'id" + commandeClientDto.getClient().getId() +  "n'existe pas en BD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        // Verifier s'il ya des lignes de commandes ->article dans la BD
        List<String> errorsMessage = new ArrayList<>();
        if(commandeClientDto.getLigneCommandeClient() != null){
            commandeClientDto.getLigneCommandeClient().forEach(ligneCmdClt ->{
                if(ligneCmdClt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCmdClt.getArticle().getId());
                    if(article.isEmpty()){
                        errorsMessage.add("l'article avec l'id" + ligneCmdClt.getArticle().getId() + " est introuvable dans la BD");
                    }
                }else {
                    errorsMessage.add("Impossible d'ajouter un article null");

                }

            });
        }
        if(!errorsMessage.isEmpty()){
            log.warn("");
            throw  new InvalidEntityException("Plusieurs articles de cette ligne de commande client sont introuvable dans la BD", ErrorCodes.ARTICLE_NOT_VALID,errorsMessage);
        }

        //Ajouter la commande client a chaque ligne de commande
        CommandeClient svaedCmdCli = commandeClientRepository.save(CommandeClientDto.fromEntityDTO(commandeClientDto));
        if(commandeClientDto.getLigneCommandeClient() != null){
            commandeClientDto.getLigneCommandeClient().forEach(cmdCli ->{
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.fromEntityDTO(cmdCli);
                ligneCommandeClient.setCommandeClient(svaedCmdCli);
            });
        }


        return CommandeClientDto.fromEntity(svaedCmdCli);
    }


    @Override
    public CommandeClientDto findById(Integer id) {
        //Verifier si l'id est null(si oui retourner un message d'erreur) sinon chercher la commande client dans la BD
        if(id == null){
           log.error("l'ID fourni est null");
           return null;
        }
       return commandeClientRepository.findById(id).map(
                CommandeClientDto::fromEntity).orElseThrow(()->{
                    throw new EntityNotFoundException("Aucune commande trouvée avec l'id" + id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);});

    }

    @Override
    public CommandeClientDto findByCode(String code) {
        //verifier ci le code n'est pas vide sinon chercher dans la BD la commande client correspondant a ce code
        if(!StringUtils.hasLength(code)){
            log.error("Le code fournit est null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code).map(CommandeClientDto::fromEntity).
                orElseThrow(()-> {
                    throw new EntityNotFoundException("Aucune commande n'a été trouvée avec ce code " + code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
                }
        );
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream().map(CommandeClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientBycommandeClientId(Integer idCommande) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return;
        }
      commandeClientRepository.deleteById(id);
    }

    @Override
    public CommandeClientDto updateEtaClient(Integer idCommande, EtatCommande etatCommande) {
        if(idCommande == null){
            log.error("l'id est null");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
        if(!StringUtils.hasLength(String.valueOf(etatCommande))){
            log.error("l'etat de la commande  est null");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
         //Verifier si la commande n'est pas livré avant de faire la modification de l'etat
        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est validée", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
        commandeClientDto.setEtatCommande(etatCommande);
        return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.fromEntityDTO(commandeClientDto)));
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLignCmd, BigDecimal quantite) {
        if(idCommande == null){
            log.error("l'id est null");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
        if(idLignCmd == null){
            log.error("l'id de la ligne de commande  est null");
            throw  new InvalidOperationException("Impossible de modifier la quantité de la commande avec un id de ligne de commande null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }

        if(quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0){
            log.error("l'id de la ligne de commande  est null");
            throw  new InvalidOperationException("Impossible de modifier la quantité de la commande avec un id de ligne de commande null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }

        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier la commande avec une quantité null ou 0", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }

       ligneCommandeClientRepository.findById(idLignCmd).map(LigneCommandeClientDto::fromEntity).orElseThrow(() ->{
           throw new EntityNotFoundException("La ligne de commande correspondant à l'id " + idLignCmd + " est introuvable", ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
       });
        LigneCommandeClient ligneCommandeClient = ligneCommandeClientRepository.findById(idLignCmd).get();
        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        if(idCommande == null){
            log.error("l'id est null");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
        if(idClient == null){
            log.error("l'id du client  est null");
            throw  new InvalidOperationException("Impossible de modifier le client avec un id  null", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }

        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
            throw  new InvalidOperationException("Impossible de modifier le client pour cette commande livrée", ErrorCodes.COMMANDE_CLIENT_NOT_MOFIFIABLE);
        }
        Optional<Client> cli = clientRepository.findById(idClient);
        if(!cli.isPresent()){
            throw new EntityNotFoundException("le client n'a pas été trouvé", ErrorCodes.CLIENT_NOT_FOUND);
        }
        commandeClientDto.setClient(ClientDto.fromEntity(cli.get()));
        return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.fromEntityDTO(commandeClientDto)));
    }

}
