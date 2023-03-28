package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {
    private Integer id;

    private String code;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVente;

    private Instant dateVente;

    private String commentaire;


    public static  VentesDto fromEntity(Ventes ventes){
        if(ventes == null){
            return null;
        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .idEntreprise(ventes.getIdEntreprise()).build();
    }

    public static Ventes fromEntityDTO(VentesDto ventesDto){
        if(ventesDto == null){
            return null;
        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setDateVente(ventesDto.getDateVente());
        ventes.setCode(ventesDto.getCode());
        ventes.setCommentaire(ventesDto.getCommentaire());
        ventes.setIdEntreprise(ventesDto.getIdEntreprise());
        return ventes;
    }
}
