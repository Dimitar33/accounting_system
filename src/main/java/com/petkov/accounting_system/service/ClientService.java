package com.petkov.accounting_system.service;

import com.petkov.accounting_system.model.Client;
import com.petkov.accounting_system.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository repo){

        this.clientRepository = repo;
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public Client getClient(Long id){
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteClient(Long id){
         clientRepository.deleteById(id);
    }
}
