package com.example.BookWorm.service;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.BeneficiaryMaster;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.InvoiceDetail;
import com.example.BookWorm.models.LibraryPackage;
import com.example.BookWorm.models.ProdBeneficiaryMaster;
import com.example.BookWorm.models.Product;
import com.example.BookWorm.models.RoyaltyCalculation;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.InvoiceDetailRepository;
import com.example.BookWorm.repository.LibraryPackageRepository;
import com.example.BookWorm.repository.ProductBeneficiaryMaster;
import com.example.BookWorm.repository.ProductRepository;
import com.example.BookWorm.repository.RoyaltyCalculationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoyaltyCalculationService {
	  @Autowired
	    private CustomerMasterRepository customerRepository;

	    @Autowired
	    private ProductRepository productRepository;

    @Autowired
    private RoyaltyCalculationRepository royaltyCalculationRepository;
     private double calculateRoyalty(LibraryPackage libraryPackage) {
        return libraryPackage.getCost() / libraryPackage.getNoofbooksallowed();
    }

    public List<RoyaltyCalculation> getAllRoyaltyCalculations() {
        return royaltyCalculationRepository.findAll();
    }

    public Optional<RoyaltyCalculation> getRoyaltyCalculationById(int id) {
        return royaltyCalculationRepository.findById(id);
    }

    public RoyaltyCalculation saveRoyaltyCalculation(RoyaltyCalculation royaltyCalculation) {
        return royaltyCalculationRepository.save(royaltyCalculation);
    }

    public void deleteRoyaltyCalculation(int id) {
        royaltyCalculationRepository.deleteById(id);
    }
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private ProductBeneficiaryMaster prodBeneficiaryMasterRepository;

    public void calculateAndSaveRoyalties(Long invoiceId) {
        List<InvoiceDetail> invoiceDetails = invoiceDetailRepository.findByInvoice_InvoiceId(invoiceId);

        for (InvoiceDetail detail : invoiceDetails) {
            Long productId = detail.getProduct().getProductId();
            Set<ProdBeneficiaryMaster> beneficiaries = prodBeneficiaryMasterRepository.findByProduct_ProductId(productId);

            double salesPrice = detail.getBasePrice(); // Assuming salesPrice is basePrice from InvoiceDetail

            for (ProdBeneficiaryMaster beneficiary : beneficiaries) {
                double percentage = beneficiary.getProdBenPercentage();
                double royaltyAmount = (percentage / 100) * salesPrice;

                RoyaltyCalculation royalty = new RoyaltyCalculation();
                royalty.setInvoice(detail.getInvoice());
                royalty.setProduct(detail.getProduct());
                royalty.setBeneficiaryMaster(beneficiary.getBeneficiary());
                royalty.setRoyaltyDate(LocalDateTime.now()); // or set the required date
//                royalty.setQuantity(detail.getQuantity()); // Assuming quantity is available
                royalty.setTransactionType(detail.getTranType()); // Assuming transaction type is available
                royalty.setSalesPrice(salesPrice);
                royalty.setBasePrice(detail.getBasePrice());
                royalty.setRoyaltyOnBasePrice(royaltyAmount);

                royaltyCalculationRepository.save(royalty);
            }
        }
    }
    @Autowired
    private LibraryPackageRepository libraryPackageRepository;

    public void calculateAndStoreRoyalty(Long customerId, Long productId) {
        // Fetch the customer
        CustomerMaster customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get the customer's library package
        LibraryPackage libraryPackage = customer.getLibraryPackage();
        if (libraryPackage == null) {
            throw new RuntimeException("Customer does not have a library package");
        }

        // Calculate the base cost
        double costPerBook = libraryPackage.getCost() / libraryPackage.getNoofbooksallowed();

        // Fetch beneficiaries for the product
        List<ProdBeneficiaryMaster> beneficiaryList = prodBeneficiaryMasterRepository.findByProduct(product);;
  Set<ProdBeneficiaryMaster> beneficiaries = new HashSet<>(beneficiaryList);
        
        if (beneficiaries.isEmpty()) {
            throw new RuntimeException("No beneficiaries found for the product");
        }

        // Calculate and save royalty for each beneficiary
        for (ProdBeneficiaryMaster prodBeneficiary : beneficiaries) {
            BeneficiaryMaster beneficiary = prodBeneficiary.getBeneficiary();
            double percentage = prodBeneficiary.getProdBenPercentage();
            double royaltyAmount = costPerBook * (percentage / 100);

            // Create and save RoyaltyCalculation entity
            RoyaltyCalculation royaltyCalculation = new RoyaltyCalculation();
            royaltyCalculation.setRoyaltyOnBasePrice(royaltyAmount);
            royaltyCalculation.setRoyaltyDate(LocalDateTime.now());
            royaltyCalculation.setProduct(product);
            royaltyCalculation.setInvoice(null); // Set the invoice if needed
            royaltyCalculation.setBeneficiaryMaster(beneficiary);
            royaltyCalculation.setTransactionType("L"); // Set transaction type to 'L'
            royaltyCalculation.setSalesPrice(costPerBook); // Or another relevant value
            royaltyCalculation.setBasePrice(costPerBook); // Base cost for the calculation

            royaltyCalculationRepository.save(royaltyCalculation);
        }
    }
}

