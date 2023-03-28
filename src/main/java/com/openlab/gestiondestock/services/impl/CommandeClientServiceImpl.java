package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.Client;
import com.openlab.gestiondestock.model.CommandeClient;
import com.openlab.gestiondestock.model.LigneCommandeClient;
import com.openlab.gestiondestock.model.dto.CommandeClientDto;
import com.openlab.gestiondestock.model.dto.LigneCommandeClientDto;
import com.openlab.gestiondestock.repository.ArticleRepository;
import com.openlab.gestiondestock.repository.ClientRepository;
import com.openlab.gestiondestock.repository.CommandeClientRepository;
import com.openlab.gestiondestock.services.CommandeClientService;
import com.openlab.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


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

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        // verifier la validation de l'objet CommandeClient -> Client -> Article

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if(!errors.isEmpty()){
            log.error("l'objet attendu est invalide");
            throw  new InvalidEntityException("L'objet attendu est ivalide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
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
    public void delete(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return;
        }
      commandeClientRepository.deleteById(id);
    }
}
