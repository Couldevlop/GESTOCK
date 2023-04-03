package com.openlab.gestiondestock.model.dto;

import com.openlab.gestiondestock.enums.RoleName;
import com.openlab.gestiondestock.model.Roles;
import com.openlab.gestiondestock.model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {
    private Integer id;
   @Enumerated(EnumType.STRING)
    private RoleName roleName;



    public static RolesDto fromEntity(Roles roles){
        if(roles == null){
            return null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .build();
    }


    public static  Roles fromEntitDTO(RolesDto rolesDto){
        if(rolesDto == null){
            return null;
        }
        Roles roles = new Roles();
         roles.setId(rolesDto.getId());
         roles.setRoleName(rolesDto.getRoleName());
       return roles;
    }
}
