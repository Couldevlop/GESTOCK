package com.openlab.gestiondestock.controller;

import com.openlab.gestiondestock.exceptions.EntityNotFoundException;
import com.openlab.gestiondestock.exceptions.ErrorCodes;
import com.openlab.gestiondestock.exceptions.InvalidEntityException;
import com.openlab.gestiondestock.model.Categorie;
import com.openlab.gestiondestock.model.dto.CategorieDto;
import com.openlab.gestiondestock.repository.CategorieRepository;
import com.openlab.gestiondestock.services.CategorieService;
import com.openlab.gestiondestock.services.impl.CategorieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CategorieControllerTest {
    private final CategorieService categorieService;




    /* -------------------------------------------------------------------------------
     * |                       TEST D'ENREGISTREMENT Categorie
     * |------------------------------------------------------------------------------
     * - Test de mise à jour =>  Reponse attendue: enregistrement ressit(test vert)
     */
    @Test
    public void shouldSaveCategorieWithSuccess(){

        CategorieDto expectedCategorie = CategorieDto.builder()
                                                  .designation("designation test")
                                                   .code("code test")
                                                   .idEntreprise(1).build();
            CategorieDto savedCategorie = categorieService.save(expectedCategorie);

        assertNotNull(savedCategorie);
        assertNotNull(savedCategorie.getId());
        assertEquals(expectedCategorie.getCode(), savedCategorie.getCode());
        assertEquals(expectedCategorie.getDesignation(), savedCategorie.getDesignation());
        assertEquals(expectedCategorie.getIdEntreprise(), savedCategorie.getIdEntreprise());

    }

    /* -------------------------------------------------------------------------------
     * |                       TEST DE MISE A JOUR Categorie
     * |------------------------------------------------------------------------------
     * - Test de mise à jour =>  Reponse attendue: enregistrement ressit(test vert)
     */

    @Test
    public void shouldUpdateCategorieWithSuccess(){

        CategorieDto expectedCategorie = CategorieDto.builder()
                .designation("designation testUpdtae")
                .code("code testUpdate")
                .idEntreprise(1).build();
        CategorieDto savedCategorie = categorieService.save(expectedCategorie);
        savedCategorie.setCode("B45172");
        CategorieDto expectedCategorie2 = categorieService.save(savedCategorie);

        assertNotNull(expectedCategorie2);
        assertNotNull(expectedCategorie2.getId());
        assertEquals(expectedCategorie2.getCode(), savedCategorie.getCode());
        assertEquals(expectedCategorie2.getDesignation(), savedCategorie.getDesignation());
        assertEquals(expectedCategorie2.getIdEntreprise(), savedCategorie.getIdEntreprise());

    }



    /* -------------------------------------------------------------------------------
     * |          TEST POUR LEVER UNE EXCEPTION POUR ENTITE INVALIDE
     * |------------------------------------------------------------------------------
     * - Test de la validité d'un objet =>  Reponse attendue: enregistrement ressit(test vert)
     */

    @Test
    public void shouldThrowInvalidEntity(){

        CategorieDto expectedCategorie = CategorieDto.builder().build();//objet vide construit

    InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, ()-> categorieService.save(expectedCategorie));
        assertEquals(ErrorCodes.CATEGORIE_NOT_VALID, expectedException.getErrorCodes());
        assertEquals(1, expectedException.getErrors().size());
        assertEquals("veillez renseigner le code de la catégorie", expectedException.getErrors().get(0));
    }


    /* -------------------------------------------------------------------------------
     * |          TEST POUR LEVER UNE EXCEPTION POUR ENTITE INTROUVABLE
     * |------------------------------------------------------------------------------
     * - Test de la présence d'un objet =>  Reponse attendue: enregistrement ressit(test vert)
     */

    @Test
    public void shouldThrowEntityNotFound(){

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, ()-> categorieService.findById(0));
        assertEquals(ErrorCodes.CATEGORIE_NOT_FOUND, expectedException.getErrorCodes());
        assertEquals("Categoirie avec l'id 0 est introuvable", expectedException.getMessage());

    }

   /* @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFound2(){
    categorieService.findById(0);
    }*/



}