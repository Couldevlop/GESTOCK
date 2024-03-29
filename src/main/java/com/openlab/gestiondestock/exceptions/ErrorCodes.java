package com.openlab.gestiondestock.exceptions;

public enum ErrorCodes {
    ARTICLE_NOT_FOUND(400),
    ARTICLE_ALREADY_IN_USED(4001),
    CLIENT_ALREADY_IN_USED(4002),
    COMMAND_CLIENT_ALREADY_IN_USED(4004),
    FOURNISSEUR_ALREADY_IN_USED(40001),
    COMMAND_FOURNISSEUR_ALREADY_IN_USED(4003),
    FOURNISSEUR_NOT_VALID(4003),
    CATEGORIE_ALREADY_IN_USED(4001),
    ARTICLE_NOT_VALID(401),
    CATEGORIE_NOT_FOUND(404),
    CATEGORIE_NOT_VALID(404),
    CLIENT_NOT_FOUND(402),
    CLIENT_NOT_VALID(402),
    COMMANDE_CLIENT_NOT_FOUND(403),
    COMMANDE_CLIENT_NOT_VALID(4001),
    COMMANDE_CLIENT_NOT_MOFIFIABLE(4001),
    COMMANDE_FOURNISSEUR_NOT_MOFIFIABLE(406),
    COMMANDE_FOURNISSEUR_NOT_FOUND(405),
    COMMANDE_FOURNISSEUR_NOT_VALID(406),
    ENTREPRISE_NOT_FOUND(6000),
    ENTREPRISE_NOT_VALID(6000),
    FOURNISSEUR_NOT_FOUND(7000),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(4004),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(4004),
    UTILSATEUR_NOT_FOUND(4004),
    UTILSATEUR_NOT_VALID(5000),
    UTILSATEUR_ALLREADY_EXIST(5002),
    UTILSATEUR_CHANGE_PASSWORD_OBJET_NOT_VALID(4004),
    VENTE_NOT_VALID(7000),
    VENTE_ALREADY_USED(70001),
    MVTSK_NOT_VALID(4004),
    UPDATE_PHOTO_EXCEPTION(4004),
    UNKNOW_CONTEXT(4000),
    VENTE_NOT_FOUND(7000);


    private int code;

    ErrorCodes(int code){
        this.code=code;
    }

    public int getCode(){
        return code;
    }
}
