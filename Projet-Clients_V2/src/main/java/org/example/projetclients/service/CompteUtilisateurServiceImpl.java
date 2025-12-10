package org.example.projetclients.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.projetclients.entities.AppRole;
import org.example.projetclients.entities.Utilisateur;
import org.example.projetclients.repositories.AppRoleRepository;
import org.example.projetclients.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CompteUtilisateurServiceImpl implements CompteUtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur addNewUser(String username, String password, String email, String confirmation) {
        Utilisateur user = utilisateurRepository.findByUsername(username);
        if(user!=null){
            throw new RuntimeException("L'utilisateur deja existe ");
        }
        if (!password.equals(confirmation)) {
            throw new RuntimeException("Mot de passe incorrecte");
        }
        user = user.builder()
                .idUtilisateur(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        return utilisateurRepository.save(user);
    }

    @Override
    public AppRole addNewRole(String roleName) {
        AppRole appRole = appRoleRepository.findById(roleName).orElse(null);
        if(appRole!=null){
            throw new RuntimeException("Le Role est deja existe ");
        }
        appRole = AppRole.builder().role(roleName).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addeRoleToUser(String username, String role) {
        Utilisateur user = utilisateurRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        user.getRoles().add(appRole);
    }

    @Override
    public void remouveRoleToUser(String username, String role) {
        Utilisateur user = utilisateurRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        user.getRoles().remove(appRole);
    }

    @Override
    public Utilisateur loadUserByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

}
