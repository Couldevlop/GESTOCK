package com.openlab.gestiondestock.security.jwt;

import com.openlab.gestiondestock.model.Utilisateur;
import com.openlab.gestiondestock.repository.UtilisateurRepository;
import com.openlab.gestiondestock.security.dto.ExtendedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("utilisateur introuvable"));
        return new ExtendedUser(utilisateur.getEmail(),utilisateur.getMotDePasse(), utilisateur.getAuthorities(), utilisateur.getEntreprise().getId());
    }
}
