package com.example.BookWorm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookWorm.models.Invoice;
import com.example.BookWorm.models.ProdBeneficiaryMaster;
import com.example.BookWorm.service.InvoiceService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")  // Enable CORS for all origins
public class InvoiceController {

	@Autowired
    private InvoiceService invoiceService;

    
    @GetMapping("/beneficiaries/invoice/{invoiceId}")
    public Set<ProdBeneficiaryMaster> getBeneficiariesByInvoiceId(@PathVariable Long invoiceId) {
        return invoiceService.getBeneficiariesForAllProducts(invoiceId);
    }
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PostMapping("/{customerId}")
    public Invoice createInvoice(@PathVariable Long customerId) {
        return invoiceService.createInvoicedetails(customerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable int id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        if (invoice.getInvoiceDate() == null || invoice.getCustomer() == null || invoice.getInvoiceAmount() == null) {
            return ResponseEntity.badRequest().build();
        }
        Invoice savedInvoice = invoiceService.saveInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        if (invoice.getInvoiceDate() == null || invoice.getCustomer() == null || invoice.getInvoiceAmount() == null) {
            return ResponseEntity.badRequest().build();
        }
        invoice.setInvoiceId(id);
        Invoice updatedInvoice = invoiceService.saveInvoice(invoice);
        return ResponseEntity.ok(updatedInvoice);
    }
//    @PostMapping("/{customerId}")
//    public ResponseEntity<Invoice> createInvoice(@PathVariable Long customerId) {
//        Invoice invoice = invoiceService.createInvoice(customerId);
//        return ResponseEntity.ok(invoice);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable int id) {
        if (invoiceService.getInvoiceById(id).isPresent()) {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
  
}
