package com.openlab.gestiondestock.exceptions;

public enum ErrorCodes {
    ARTICLE_NOT_FOUND(400),
    ARTICLE_NOT_VALID(401),
    CATEGORIE_NOT_FOUND(404),
    CATEGORIE_NOT_VALID(404),
    CLIENT_NOT_FOUND(402),
    CLIENT_NOT_VALID(402),
    COMMANDE_CLIENT_NOT_FOUND(403),
    COMMANDE_CLIENT_NOT_VALID(4001),
    COMMANDE_FOURNISSEUR_NOT_FOUND(405),
    COMMANDE_FOURNISSEUR_NOT_VALID(406),
    ENTREPRISE_NOT_FOUND(6000),
    ENTREPRISE_NOT_VALID(6000),
    FOURNISSEUR_NOT_FOUND(7000),
    VENTE_NOT_VALID(7000),
    VENTE_NOT_FOUND(7000);

    private int code;

    ErrorCodes(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }
}
