package org.example.projetclients.service;

import org.example.projetclients.entities.AppRole;
import org.example.projetclients.entities.Utilisateur;

public interface CompteUtilisateurService {
    Utilisateur addNewUser(String username, String password , String email , String confirmation);
    AppRole addNewRole(String role);
    void addeRoleToUser(String username , String role);
    void remouveRoleToUser(String username ,String role);
    Utilisateur loadUserByUsername(String username);

}
