package com.example.BookWorm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookWorm.models.Product_Type_Attribute_Value;
import com.example.BookWorm.service.ProductTypeAttributeValueService;

@RestController
@RequestMapping("/api/product-type-attribute-values")
public class ProductTypeAttributeValueController {
	 @Autowired
	    private ProductTypeAttributeValueService service;
	 @GetMapping("/product/{productId}")
	    public ResponseEntity<List<Product_Type_Attribute_Value>> getAttributesByProductId(@PathVariable Long productId) {
	        List<Product_Type_Attribute_Value> attributes = service.getAttributesByProductId(productId);
	        if (attributes.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(attributes, HttpStatus.OK);
	    }
}
