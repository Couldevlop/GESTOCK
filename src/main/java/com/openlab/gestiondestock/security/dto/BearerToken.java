package com.openlab.gestiondestock.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BearerToken {
    private String accessToken ;
    private String tokenType ;
}
