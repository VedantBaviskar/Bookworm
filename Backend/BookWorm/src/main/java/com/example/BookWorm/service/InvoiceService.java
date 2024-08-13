package com.example.BookWorm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.CartDetails;
import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.Invoice;
import com.example.BookWorm.models.InvoiceDetail;
import com.example.BookWorm.models.MyShelf;
import com.example.BookWorm.models.ProdBeneficiaryMaster;
import com.example.BookWorm.models.Product;
import com.example.BookWorm.models.ProductOnShelf;
import com.example.BookWorm.repository.CartDetailsRepository;
import com.example.BookWorm.repository.CartMasterRepository;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.InvoiceDetailRepository;
import com.example.BookWorm.repository.InvoiceRepository;
import com.example.BookWorm.repository.MyShelfRepository;
import com.example.BookWorm.repository.ProductBeneficiaryMaster;
import com.example.BookWorm.repository.ProductOnShelfRepository;
import com.example.BookWorm.repository.ProductRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private CustomerMasterRepository customerMasterRepository;
    @Autowired
    private CartDetailsRepository cartDetailsRepository;
    @Autowired
    private CustomerMasterService customerService;
    // Retrieves all invoices
    @Autowired
private ProductBeneficiaryMaster productbeneficiarymaster;   
    public Set<ProdBeneficiaryMaster> getBeneficiariesForAllProducts(Long invoiceId) {
        List<InvoiceDetail> invoiceDetails = invoiceDetailRepository.findByInvoice_InvoiceId(invoiceId);
        Set<ProdBeneficiaryMaster> beneficiaries = new HashSet<>();

        for (InvoiceDetail detail : invoiceDetails) {
            Long productId = detail.getProduct().getProductId();
            beneficiaries.addAll(productbeneficiarymaster.findByProduct_ProductId(productId));
        }

        return beneficiaries;
    }
    public List<Invoice> getInvoicesByCustomerId(Long customerId) {
        return invoiceRepository.findByCustomer_CustomerId(customerId);
    }
    public Invoice createInvoicedetails(Long customerId) {
        // Find customer by ID
        CustomerMaster customer = customerMasterRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Get cart ID from customer
        CartMaster cart = customer.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart not found for customer");
        }

        // Get cart details for the cart
        List<CartDetails> cartDetailsList = cartDetailsRepository.findByCid(cart);

        // Create new invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setCustomer(customer);
        invoice.setInvoiceAmount(cart.getCost());

        // Save invoice
        invoice = invoiceRepository.save(invoice);

        // Create invoice details
        for (CartDetails cartDetails : cartDetailsList) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setInvoice(invoice);
            invoiceDetail.setProduct(cartDetails.getProduct());
            invoiceDetail.setQuantity(null);  // Set quantity as null
            invoiceDetail.setBasePrice(cartDetails.getProduct().getProductSpCost());
            invoiceDetail.setTranType("P");
            invoiceDetail.setRentNoOfDays(null);  // Set rentNoOfDays as null

            // Save invoice detail
            invoiceDetailRepository.save(invoiceDetail);
        }

        return invoice;
    }
//    public Invoice createInvoice(Long customerId) {
//        CustomerMaster customer = customerMasterRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        CartMaster cart = customer.getCart();
//        if (cart == null) {
//            throw new RuntimeException("Cart not found for the customer");
//        }
//
//        double totalAmount = cart.getCost();
//        LocalDate today = LocalDate.now();
//
//        Invoice invoice = new Invoice();
//        invoice.setCustomer(customer);
//        invoice.setInvoiceAmount(totalAmount);
//        invoice.setInvoiceDate(today);
//
//        return invoiceRepository.save(invoice);
//    }
    
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
//    @Transactional
//    public Invoice createInvoice(Long customerId) {
//        // Fetch the cart details for the customer
//        CartMaster cart = cartMasterRepository.findByCustomerId(customerId);
//        
//        if (cart == null) {
//            throw new RuntimeException("Cart not found for customer ID: " + customerId);
//        }
//
//        // Create a new invoice
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceDate(LocalDate.now());
//        invoice.setCustomer(new CustomerMaster(customerId)); // Assuming you have a CustomerMaster constructor
//        invoice.setInvoiceAmount(cart.getCost());
//
//        // Save the invoice
//        return invoiceRepository.save(invoice);
//    }
    // Retrieves an invoice by its ID
    public Optional<Invoice> getInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    // Saves a new invoice or updates an existing one
    public Invoice saveInvoice(Invoice invoice) {
        // Validate required fields
        if (invoice.getInvoiceDate() == null) {
            throw new IllegalArgumentException("Invoice date cannot be null");
        }
        if (invoice.getCustomer() == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (invoice.getInvoiceAmount() == null) {
            throw new IllegalArgumentException("Invoice amount cannot be null");
        }
        return invoiceRepository.save(invoice);
    }

    // Deletes an invoice by its ID
    public void deleteInvoice(int id) {
        invoiceRepository.deleteById(id);
    }
  
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MyShelfRepository myShelfRepository;

    @Autowired
    private ProductOnShelfRepository productOnShelfRepository;


}
