package com.petkov.accounting_system.controller;

import com.petkov.accounting_system.model.Invoice;
import com.petkov.accounting_system.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service){
        this.service = service;
    }

    @GetMapping
    public List<Invoice> getAllInvoices(){
        return service.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Invoice getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Invoice> getAllByClient(@PathVariable Long clientId){
        return service.getInvoicesByClient(clientId);
    }

    @PostMapping("/{clientId}")
    public Invoice createInvoice (@PathVariable Long clientId, @RequestBody Invoice invoice ){
        return service.create(invoice, clientId);
    }

    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id){
        service.deleteInvoice(id);
        return "Invoice deleted";
    }

    @DeleteMapping("/client/{clientId}")
    public String deleteByClient(@PathVariable Long clientId){

        service.deleteByClient(clientId);

        return "All clients invoices deleted";
    }
}
