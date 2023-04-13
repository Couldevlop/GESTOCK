package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.exceptions.InvalidOperationException;
import com.openlab.gestiondestock.model.CommandeClient;
import com.openlab.gestiondestock.model.dto.ClientDto;
import com.openlab.gestiondestock.repository.ClientRepository;
import com.openlab.gestiondestock.repository.CommandeClientRepository;
import com.openlab.gestiondestock.services.ClientService;
import com.openlab.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CommandeClientRepository commandeClientRepository;

    public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        /*
        * Verification de la validité de l'objet
         */

        List<String> errors = ClientValidator.validate(clientDto);
        if(!errors.isEmpty()){
            log.error("l'objet est vide {}" + clientDto);
            throw new InvalidEntityException("L'objet attendu est invalide", ErrorCodes.CLIENT_NOT_VALID,errors);
        }

        return ClientDto.fromEntity(clientRepository.save(ClientDto.fromEntityDTO(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if( id == null){
            log.error("l'id est null");
            return null;
        }
        return clientRepository.findById(id).map(ClientDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("Le iclient avec l'id" + id + "est introuvable", ErrorCodes.CLIENT_NOT_FOUND);
        });
    }

    @Override
    public ClientDto findByEmail(String email) {
        if(email == null){
            log.error("le code fourni est null");
            return null;
        }
        return clientRepository.findByEmail(email).map(ClientDto::fromEntity).orElseThrow(()->{
            throw new EntityNotFoundException("Aucun client ne correspond au code fourni",ErrorCodes.CLIENT_NOT_FOUND);
        });
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("l'id est null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findByAllClientId(id);
        if(!commandeClients.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer le client car il est associé à des commandes", ErrorCodes.CLIENT_ALREADY_IN_USED);
        }
      clientRepository.deleteById(id);
    }
}
