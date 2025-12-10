package org.example.projetclients.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Utilisateur {
    @Id
    private String idUtilisateur;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    //charger tous les atributs et le role quand on chercher utilisateur
    private List <AppRole> roles;
}
