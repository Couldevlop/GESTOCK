package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.Article;
import com.openlab.gestiondestock.model.LigneVente;
import com.openlab.gestiondestock.model.Ventes;
import com.openlab.gestiondestock.model.dto.LigneVenteDto;
import com.openlab.gestiondestock.model.dto.VentesDto;
import com.openlab.gestiondestock.repository.ArticleRepository;
import com.openlab.gestiondestock.repository.LigneVenteRepository;
import com.openlab.gestiondestock.repository.VenteRepository;
import com.openlab.gestiondestock.services.VenteService;
import com.openlab.gestiondestock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private final ArticleRepository articleRepository;
    private final LigneVenteRepository ligneVenteRepository;

    public VenteServiceImpl(VenteRepository venteRepository,
                            ArticleRepository articleRepository,
                            LigneVenteRepository ligneVenteRepository) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {

        /* Vérifier  la validation de l'objet VENTE
        * VALIDE: suite du processus d'enregistrement
        * INVALID: lever une exception avec un message personnalisé et interruption du processus
         */

        List<String> venteErrors = VenteValidator.validate(ventesDto);
        if(!venteErrors.isEmpty()){
            log.error("L'objet fourni est invalide");
            throw new InvalidEntityException("Objet invalid", ErrorCodes.VENTE_NOT_VALID,venteErrors);
        }

        /* verifier s'il y'a un article dans chaque ligne de vente
        * NON: lever une exception avec un message d'erreur personnalisé et interrompre le processus d'enregistrement
        * OUI: continuer le traitement en passant à l'étape suivante
         */

        List<String> articleErrors = new ArrayList<>();
        ventesDto.getLigneVente().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if(article.isPresent())
                articleErrors.add("Un ou plusieurs articles sont abscents pour l'id " + ligneVenteDto.getArticle().getId() + "dans la BD");
        });

        if(!articleErrors.isEmpty()){
            log.error("Un ou plusieurs articles sont abscents ");
            throw new InvalidEntityException("Un ou plusieurs articles sont abscents dans la BD", ErrorCodes.VENTE_NOT_FOUND, articleErrors);
        }
        /*Enregistrement de la vente
        * sauvegarde de la vente
        * sauvegarder la vente dans la ligne de vente
        */

        Ventes v = venteRepository.save(VentesDto.fromEntityDTO(ventesDto));
        ventesDto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.fromEntity(ligneVenteDto);
            ligneVente.setVentes(v);
            ligneVenteRepository.save(ligneVente);
        });

        return VentesDto.fromEntity(v);
    }

    @Override
    public VentesDto findById(Integer id) {

        //Verifier si l'id n'est pas

        if(id == null){
            log.error("l'id est null");
            return null;
        }


        /* verifier l'exisstance de l'objetdont l'id a été fourni
        *  OUI: retourner un message d'erreur
        *  NON: ramener de la base de données l'objet vente
         */

      return   venteRepository.findById(id).map(
                VentesDto::fromEntity).orElseThrow(()->{
             log.error("Aucun objet vente trouvé avec l'id " + id + "dans la BD");
            throw new EntityNotFoundException("Aucun objet vente trouvé avec l'id " + id + "dans la BD", ErrorCodes.VENTE_NOT_FOUND);
        });

    }

    @Override
    public VentesDto findByCode(String code) {

        //Verifier si le code fourni n'est pas null

        if(code == null){
            log.error("le code fourni est null");
            return null;
        }
        return venteRepository.findByCode(code).map(VentesDto::fromEntity).orElseThrow(() ->{
            log.error("Aucun objet vente trouvé avec le code " + code + "dans la BD");
            throw new EntityNotFoundException("Aucun objet vente trouvé avec le code " + code + "dans la BD", ErrorCodes.VENTE_NOT_FOUND);
        });
    }

    @Override
    public List<VentesDto> findAll() {
        return venteRepository.findAll().stream().map(VentesDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        //verifier si l'id est null

        if(id == null){
            log.error("l'id fourni est null");
            return;
        }
        venteRepository.deleteById(id);


    }
}
