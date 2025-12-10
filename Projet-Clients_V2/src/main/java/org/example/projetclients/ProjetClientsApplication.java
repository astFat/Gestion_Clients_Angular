package org.example.projetclients;

import org.example.projetclients.entities.Client;
import org.example.projetclients.repositories.ClientRepository;
import org.example.projetclients.service.CompteUtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class ProjetClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetClientsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(Client.builder()
                    .nom("ALAMI")
                    .age(35)
                    .build());
            clientRepository.save(Client.builder()
                    .nom("BENNANI")
                    .age(30)
                    .build());
            clientRepository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner userDetailsPersonel(CompteUtilisateurService compteUtilisateurService) {
        return args -> {
            compteUtilisateurService.addNewRole("USER");
            compteUtilisateurService.addNewRole("ADMIN");
            compteUtilisateurService.addNewUser(
                    "user1","1234","user@gmail.com","1234");
            compteUtilisateurService.addNewUser(
                    "user2","1234","user2@gmail.com","1234");
            compteUtilisateurService.addNewUser(
                    "admin","1234","admin@gmail.com","1234");
            compteUtilisateurService.addeRoleToUser("user1","USER");
            compteUtilisateurService.addeRoleToUser("user2","USER");
            compteUtilisateurService.addeRoleToUser("admin","ADMIN");
            compteUtilisateurService.addeRoleToUser("admin","USER");
        };
    }
}