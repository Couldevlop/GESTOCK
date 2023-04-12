package com.openlab.gestiondestock.services.impl;

import com.openlab.gestiondestock.enums.TypeMvtStk;
import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.MvtStk;
import com.openlab.gestiondestock.model.dto.FournisseurDto;
import com.openlab.gestiondestock.model.dto.MvtStkDto;
import com.openlab.gestiondestock.repository.MvtStkRepository;
import com.openlab.gestiondestock.services.ArticleService;
import com.openlab.gestiondestock.services.MvtStkService;
import com.openlab.gestiondestock.validator.MvtStkValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.openlab.gestiondestock.enums.TypeMvtStk.ENTREE;

@Service
@Slf4j
@RequiredArgsConstructor
public class MvtStkServiceImpl implements MvtStkService {
    private final MvtStkRepository mvtStkRepository;
    private final ArticleService articleService;

    @Override
    public BigDecimal sotckReelArticle(Integer idArticle) {
        if(idArticle == null){
            log.warn("l'id est null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStkRepository.sotckReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Integer idArticle) {
        if(idArticle == null){
            log.warn("l'id est null");
            return null;
        }
        return mvtStkRepository.findAllByArticleId(idArticle).stream().map(MvtStkDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return  entreePositive(TypeMvtStk.ENTREE, dto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return  entreeNegative(TypeMvtStk.SORTIE, dto);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        return entreePositive(TypeMvtStk.CORRECTION_POS, dto);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return entreeNegative(TypeMvtStk.CORRECTION_NEG, dto);
    }

    private MvtStkDto entreePositive(TypeMvtStk typeMvtStk, MvtStkDto dto){
        List<String> errors = MvtStkValidator.validate(dto);
        if(!errors.isEmpty()){
            throw  new InvalidEntityException("L'objet fourni est introuvable", ErrorCodes.MVTSK_NOT_VALID, errors);
        }
        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
        dto.setTypeMvtStk(typeMvtStk);
        return MvtStkDto.fromEntity(mvtStkRepository.save(MvtStkDto.fromEntityDTO(dto)));
    }

    private MvtStkDto entreeNegative(TypeMvtStk typeMvtStk, MvtStkDto dto){
        List<String> errors = MvtStkValidator.validate(dto);
        if(!errors.isEmpty()){
            throw  new InvalidEntityException("L'objet fourni est introuvable", ErrorCodes.MVTSK_NOT_VALID, errors);
        }
        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue()) * -1));
        dto.setTypeMvtStk(typeMvtStk);
        return MvtStkDto.fromEntity(mvtStkRepository.save(MvtStkDto.fromEntityDTO(dto)));
    }
}
