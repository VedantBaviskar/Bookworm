package com.example.BookWorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.ProductGenre;
import com.example.BookWorm.models.Product_Type_Attribute;
import com.example.BookWorm.repository.ProductGenreRepository;
import com.example.BookWorm.repository.ProductTypeRepository;
@Service
public class ProductTypeAttrService {
	@Autowired
    private ProductTypeRepository productGenreRepository;
	public List<Product_Type_Attribute> getAllProductGenres() {
        List<Product_Type_Attribute> productGenres = productGenreRepository.findAll();
        productGenres.forEach(pg -> System.out.println("Retrieved ProductTypeAttribute: " + pg));
        return productGenres;
    }

    public List<Product_Type_Attribute> getAttributesByProductTypeId(int productTypeId) {
        return productGenreRepository.findByProductTypeId(productTypeId);
    }
}
