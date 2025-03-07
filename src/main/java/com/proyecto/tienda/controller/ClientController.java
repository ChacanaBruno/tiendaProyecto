package com.proyecto.tienda.controller;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.service.client.IClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ClientController {
    private IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/fetch")
    public List<Client> getClients() {

        return clientService.getClients();
    }

    @PostMapping("/clients/create")
    public String createClient(@RequestBody Client client) {

        clientService.saveClient(client);

        return "Successfully created client";
    }

    @DeleteMapping("/clients/delete/{id}")
    public String deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return "Successfully deleted client";
    }

    @GetMapping("/clients/find/{id}")
    public Client findClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @PutMapping("/clients/edit/{code_product}")
    public Client editClient(@PathVariable Long code_product,
                             @RequestBody ClientUpdateDTO clientUpdateDTO) {

        clientService.editClient(code_product, clientUpdateDTO);

        return clientService.findClientById(code_product);
    }
}
