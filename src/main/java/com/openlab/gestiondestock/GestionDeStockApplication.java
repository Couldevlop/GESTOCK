package com.openlab.gestiondestock;

import com.openlab.gestiondestock.enums.RoleName;
import com.openlab.gestiondestock.model.Roles;
import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.model.dto.UtilisateurDto;
import com.openlab.gestiondestock.repository.RolesRepository;
import com.openlab.gestiondestock.repository.UtilisateurRepository;
import com.openlab.gestiondestock.security.service.IUtilisateurService;
import com.openlab.gestiondestock.security.service.UtilisateurSecurServiceImpl;
import com.openlab.gestiondestock.services.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class GestionDeStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDeStockApplication.class, args);
    }
    @Bean
    CommandLineRunner run (IUtilisateurService iUserService , RolesRepository iRoleRepository , UtilisateurRepository iUserRepository , PasswordEncoder passwordEncoder)
    {return  args ->
    {
        iUserService.saveRole(new Roles(RoleName.USER));
        iUserService.saveRole(new Roles(RoleName.ADMIN));
        iUserService.saveRole(new Roles(RoleName.SUPERADMIN));
        iUserService.saverUser(new Utilisateur("admin@gmail.com", passwordEncoder.encode("adminPassword"), new ArrayList<>()));
        iUserService.saverUser(new Utilisateur("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new ArrayList<>()));

        Roles role = iRoleRepository.findByRoleName(RoleName.ADMIN);
        Utilisateur user = iUserRepository.findByEmail("admin@gmail.com").orElse(null);
        user.getRoles().add(role);
        Utilisateur u1 =iUserService.saverUser(user);
        u1.getRoles().forEach(r ->{
            r.setUtilisateur(u1);
            iRoleRepository.save(r);
        });

        Utilisateur userr = iUserRepository.findByEmail("superadminadmin@gmail.com").orElse(null);
        Roles rolee = iRoleRepository.findByRoleName(RoleName.SUPERADMIN);
        userr.getRoles().add(rolee);
        Utilisateur u2=iUserService.saverUser(userr);
        u2.getRoles().forEach(r ->{
            r.setUtilisateur(u2);
            iRoleRepository.save(r);
        });

    };}
}
