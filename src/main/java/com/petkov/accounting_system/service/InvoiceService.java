package com.petkov.accounting_system.service;

import com.petkov.accounting_system.model.Client;
import com.petkov.accounting_system.model.Invoice;
import com.petkov.accounting_system.repository.ClientRepository;
import com.petkov.accounting_system.repository.InvoiceRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final ClientRepository clientRepository;

    public InvoiceService(InvoiceRepo invoiceRepo, ClientRepository clientRepository){

        this.invoiceRepo = invoiceRepo;
        this.clientRepository = clientRepository;
    }

    public List<Invoice> getAllInvoices(){
        return invoiceRepo.findAll();
    }

    public Invoice getById(Long id){
        return invoiceRepo.findById(id).orElse(null);
    }

    public List<Invoice> getInvoicesByClient(Long clientId){

        return invoiceRepo.findByClientId(clientId);
    }

    public Invoice create(Invoice invoice, Long clientId){

        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new RuntimeException("Client not found"));
        invoice.setClient(client);
        return invoiceRepo.save(invoice);
    }

    public void deleteInvoice(Long id){
        invoiceRepo.deleteById(id);
    }

    @Transactional
    public void deleteByClient(Long clientId){
        invoiceRepo.deleteInvoicesByClientId(clientId);
    }

}
