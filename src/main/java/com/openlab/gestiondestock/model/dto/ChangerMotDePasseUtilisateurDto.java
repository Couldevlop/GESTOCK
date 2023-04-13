package com.openlab.gestiondestock.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangerMotDePasseUtilisateurDto {
    private Integer id;
    private String password;
    private String confirmPassord;
}
