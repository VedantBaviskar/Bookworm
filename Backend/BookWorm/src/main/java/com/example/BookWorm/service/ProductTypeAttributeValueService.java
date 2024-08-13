package com.example.BookWorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.Product_Type_Attribute_Value;
import com.example.BookWorm.repository.Product_Type_Attribute_Value_Repository;

@Service
public class ProductTypeAttributeValueService {
	@Autowired
    private Product_Type_Attribute_Value_Repository repository;

    public List<Product_Type_Attribute_Value> getAttributesByProductId(Long productId) {
        return repository.findByProductId(productId);
    }
}
