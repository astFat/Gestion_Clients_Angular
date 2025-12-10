package org.example.projetclients.web;

import org.example.projetclients.entities.Client;
import org.example.projetclients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200" )
public class ClientRestController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping({"/clients"})
    public List <Client> listeClient (@RequestParam(defaultValue = "") String keyword){
        if (keyword.isEmpty()) {
            return clientRepository.findAll();
        }
        // Assuming clientRepository has a method findClientByNomContains, which is common in Spring Data JPA
        // You must ensure this method exists in your ClientRepository interface.
        return clientRepository.findClientByNomContains(keyword);
    }

    @DeleteMapping("/clients/{id}")
    public List<Client> deleteClient (@PathVariable Long id){
        Client client =clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client chercherParIdClient (@PathVariable Long id){
        return clientRepository.findById(id).orElseThrow();
    }

    @PostMapping("/clients")
    public Client addClient (@RequestBody Client client){
        Client c = client;
        c.setNom(client.getNom());
        c.setAge(client.getAge());
        return clientRepository.save(c);
    }

    @PutMapping("/clients/{id}")
    public Client modifierClient (@RequestBody Client client , @PathVariable Long id){
        Client c =clientRepository.findById(id).orElseThrow();
        if(client.getNom() != null) c.setNom(client.getNom());
        if(client.getAge()!= null) c.setAge(client.getAge());
        return clientRepository.save(c);
    }
}
