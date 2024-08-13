package com.example.BookWorm.service;



import com.example.BookWorm.models.ProductType;
import com.example.BookWorm.repository.ProductTypeRepo;
import com.example.BookWorm.repository.ProductTypeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepo productTypeRepository;

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> getProductTypeById(int id) {
        return productTypeRepository.findById(id);
    }

    public ProductType saveProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public void deleteProductType(int id) {
        productTypeRepository.deleteById(id);
    }
}
