package com.petkov.accounting_system.repository;

import com.petkov.accounting_system.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    List<Invoice> findByClientId(Long clientId);

    void deleteInvoicesByClientId(Long clientId);
}
