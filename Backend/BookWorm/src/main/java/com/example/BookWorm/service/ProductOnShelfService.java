package com.example.BookWorm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.MyShelf;
import com.example.BookWorm.models.ProductOnShelf;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.ProductOnShelfRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOnShelfService {

    @Autowired
    private ProductOnShelfRepository productOnShelfRepository;

    public List<ProductOnShelf> getAllProductsOnShelf() {
        return productOnShelfRepository.findAll();
    }

    public Optional<ProductOnShelf> getProductOnShelfById(Long id) {
        return productOnShelfRepository.findById(id);
    }
    @Autowired
    private CustomerMasterRepository customerMasterRepository;
    public List<ProductOnShelf> getProductsOnShelfByCustomerId(Long customerId) {
        CustomerMaster customer = customerMasterRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        
        MyShelf shelf = customer.getShelf();
        
        if (shelf == null) {
            throw new RuntimeException("Shelf not found for customer id: " + customerId);
        }
        
        return productOnShelfRepository.findByShelf(shelf);
    }
    public ProductOnShelf saveProductOnShelf(ProductOnShelf productOnShelf) {
        return productOnShelfRepository.save(productOnShelf);
    }

    public void deleteProductOnShelf(Long id) {
        productOnShelfRepository.deleteById(id);
    }
}
