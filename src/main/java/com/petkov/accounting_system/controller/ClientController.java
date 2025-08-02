package com.petkov.accounting_system.controller;

import com.petkov.accounting_system.model.Client;
import com.petkov.accounting_system.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id){

         clientService.deleteClient(id);
        return "Client deleted";
    }
}
