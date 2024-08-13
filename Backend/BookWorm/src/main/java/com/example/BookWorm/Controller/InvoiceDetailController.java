package com.example.BookWorm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BookWorm.models.Invoice;
import com.example.BookWorm.models.InvoiceDetail;
import com.example.BookWorm.service.InvoiceDetailService;
import com.example.BookWorm.service.InvoiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice-details")
@CrossOrigin(origins = "*")  // Enable CORS for all origins
public class InvoiceDetailController {

    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping
    public List<InvoiceDetail> getAllInvoiceDetails() {
        return invoiceDetailService.getAllInvoiceDetails();
    }
    @DeleteMapping("/invoice/{invoiceId}")
    public ResponseEntity<Void> deleteInvoiceDetails(@PathVariable Long invoiceId) {
        invoiceDetailService.deleteInvoiceDetailsByInvoiceId(invoiceId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/customer/{customerId}")
    public List<InvoiceDetail> getInvoiceDetailsByCustomerId(@PathVariable Long customerId) {
        List<Invoice> invoices = invoiceService.getInvoicesByCustomerId(customerId);
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        for (Invoice invoice : invoices) {
            invoiceDetails.addAll(invoiceDetailService.getInvoiceDetailsByInvoiceId(invoice.getInvoiceId()));
        }
        return invoiceDetails;
    }
    @GetMapping("/{id}")
    public Optional<InvoiceDetail> getInvoiceDetailById(@PathVariable int id) {
        return invoiceDetailService.getInvoiceDetailById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public InvoiceDetail createInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail) {
        if (invoiceDetail.getInvoice() == null || invoiceDetail.getProduct() == null) {
            throw new IllegalArgumentException("Invoice and Product cannot be null");
        }
        return invoiceDetailService.saveInvoiceDetail(invoiceDetail);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public InvoiceDetail updateInvoiceDetail(@PathVariable Long id, @RequestBody InvoiceDetail invoiceDetail) {
        if (invoiceDetail.getInvoice() == null || invoiceDetail.getProduct() == null) {
            throw new IllegalArgumentException("Invoice and Product cannot be null");
        }
        invoiceDetail.setInvDtlId(id);
        return invoiceDetailService.saveInvoiceDetail(invoiceDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoiceDetail(@PathVariable int id) {
        invoiceDetailService.deleteInvoiceDetail(id);
    }
}
