package com.openlab.gestiondestock.security.service;

import com.openlab.gestiondestock.enums.RoleName;
import com.openlab.gestiondestock.model.Roles;
import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.repository.RolesRepository;
import com.openlab.gestiondestock.repository.UtilisateurRepository;
import com.openlab.gestiondestock.security.dto.BearerToken;
import com.openlab.gestiondestock.security.dto.ExtendedUser;
import com.openlab.gestiondestock.security.dto.LoginDto;
import com.openlab.gestiondestock.security.dto.RegisterDto;
import com.openlab.gestiondestock.security.jwt.JwtUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UtilisateurSecurServiceImpl implements IUtilisateurService{

    private final RolesRepository rolesRepository;
    private final  UtilisateurRepository utilisateurRepository;
    private final AuthenticationManager authenticationManger;
    //private final Pbkdf2PasswordEncoder passwordEncoder;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities ;



    @Override
    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManger.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Utilisateur user = utilisateurRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("Utilsateur introuvable"));

        List<Roles> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r));
        String token = jwtUtilities.generateToken(user.getUsername(), rolesNames);
        log.info("utilisateur bien connecté" + LocalDateTime.now());
        return token;
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if(utilisateurRepository.existsByEmail(registerDto.getEmail()))
        {
            return  new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER);
        }
        else
        {
            Utilisateur user = new Utilisateur();
            user.setEmail(registerDto.getEmail());
            user.setNom(registerDto.getNom());
            user.setPrenom(registerDto.getPrenom());
            user.setMotDePasse(passwordEncoder.encode(registerDto.getPassword()));
            //By Default , he/she is a simple user
            Roles role = rolesRepository.findByRoleName(RoleName.USER);
            user.setRoles(Collections.singletonList(role));
            utilisateurRepository.save(user);
            String token = jwtUtilities.generateToken(registerDto.getEmail(),Collections.singletonList(role));
            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK); }
    }

    @Override
    public Roles saveRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Override
    public Utilisateur saverUser(Utilisateur user) {
        return utilisateurRepository.save(user);
    }
}
