package com.openlab.gestiondestock.security.dto;

import lombok.Data;

@Data
public class BearerToken {
    private String accessToken ;
    private String tokenType ;
}
