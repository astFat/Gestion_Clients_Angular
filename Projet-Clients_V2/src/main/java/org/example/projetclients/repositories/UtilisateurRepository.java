package org.example.projetclients.repositories;

import org.example.projetclients.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {
    public Utilisateur findByUsername(String username);
}
