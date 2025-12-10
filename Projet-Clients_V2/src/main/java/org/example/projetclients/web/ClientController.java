package org.example.projetclients.web;


import jakarta.validation.Valid;
import org.example.projetclients.entities.Client;
import org.example.projetclients.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;

    @GetMapping({"/user/index", "/"})
    public String listeClient(Model model,@RequestParam(defaultValue = "") String keyword) {
        List<Client> clients = clientRepository.findClientByNomContains(keyword);
        model.addAttribute("clients", clients);
        model.addAttribute("keyword", keyword);
        return "listeClients";
    }

    @GetMapping({"/admin/delete"})
    public String deleteClient(@RequestParam Long id) {
        clientRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping({"/admin/nouveauClient"})
    public String ajouterClient(Model model) {
        model.addAttribute("client", new Client());
        return "nouveauClient";
    }

    @PostMapping("/admin/enregistrerClient")
    public String saveClient(@Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "nouveauClient";}
        clientRepository.save(client);
        return "redirect:/user/index";
    }

    @GetMapping({"/admin/edit"})
    public String editerClient(Model model, @RequestParam Long id) {
        Client client = clientRepository.findById(id).get();
        model.addAttribute("client", client);
        return "editerClient";
    }

}
