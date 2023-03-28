package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.model.Roles;
import com.openlab.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@Data
public class RolesDto {
    private Integer id;

    private String roleName;

    private UtilisateurDto utilisateur;
    private Integer idEntreprise;

    public static RolesDto fromEntity(Roles roles){
        if(roles == null){
            return null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .utilisateur(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                .build();
    }


    public static  Roles fromEntitDTO(RolesDto rolesDto){
        if(rolesDto == null){
            return null;
        }
        Roles roles = new Roles();
         roles.setId(rolesDto.getId());
         roles.setRoleName(rolesDto.getRoleName());
        roles.setIdEntreprise(rolesDto.getIdEntreprise());
        roles.setUtilisateur(UtilisateurDto.fromEntityDTO(rolesDto.getUtilisateur()));
       return roles;
    }
}
