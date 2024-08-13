package com.example.BookWorm.Controller;


import com.example.BookWorm.models.ProductType;
import com.example.BookWorm.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-types")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/getall")
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable int id) {
        Optional<ProductType> productType = productTypeService.getProductTypeById(id);
        return productType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        ProductType savedProductType = productTypeService.saveProductType(productType);
        return new ResponseEntity<>(savedProductType, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable int id, @RequestBody ProductType productType) {
        if (!productTypeService.getProductTypeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productType.setId(id);
        ProductType updatedProductType = productTypeService.saveProductType(productType);
        return new ResponseEntity<>(updatedProductType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable int id) {
        if (!productTypeService.getProductTypeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }
}
