package org.example.projetclients.service;

import lombok.AllArgsConstructor;
import org.example.projetclients.entities.Utilisateur;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserDatilService implements UserDetailsService {
    private CompteUtilisateurService compteUtilisateurService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = compteUtilisateurService.loadUserByUsername(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException(username+" not found");
        }
        UserDetails userDetails = User
                .withUsername(utilisateur.getUsername())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRoles().stream()
                .map(role->role.getRole()).toArray(String[]::new))
                .build();
        return userDetails ;
    }
}
