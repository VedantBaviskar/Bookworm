package com.example.BookWorm.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookWorm.models.ProductGenre;
import com.example.BookWorm.models.Product_Type_Attribute;
import com.example.BookWorm.service.ProductGenreService;
import com.example.BookWorm.service.ProductTypeAttrService;

@RestController
@RequestMapping("/api/producttypeattr")
public class ProductTypeAttributeController {
	 @Autowired
	    private ProductTypeAttrService productGenreService;
	 @GetMapping
	    public ResponseEntity<List<Product_Type_Attribute>> getAllProductGenres() {
	        List<Product_Type_Attribute> productGenres = productGenreService.getAllProductGenres();
	        return new ResponseEntity<>(productGenres, HttpStatus.OK);
	    }
	 @GetMapping("/{productTypeId}")
	    public List<Product_Type_Attribute> getAttributesByProductTypeId(@PathVariable int productTypeId) {
	        return productGenreService.getAttributesByProductTypeId(productTypeId);
	    }
}
