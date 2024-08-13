package com.example.BookWorm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookWorm.models.ProductOnShelf;
import com.example.BookWorm.service.ProductOnShelfService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products-on-shelf")
@CrossOrigin(origins = "*")  // Enable CORS for all origins
public class ProductOnShelfController {

    @Autowired
    private ProductOnShelfService productOnShelfService;

    @GetMapping
    public List<ProductOnShelf> getAllProductsOnShelf() {
        return productOnShelfService.getAllProductsOnShelf();
    }
    @GetMapping("/customer/{customerId}")
    public List<ProductOnShelf> getProductsOnShelf(@PathVariable Long customerId) {
        return productOnShelfService.getProductsOnShelfByCustomerId(customerId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductOnShelf> getProductOnShelfById(@PathVariable Long id) {
        Optional<ProductOnShelf> productOnShelf = productOnShelfService.getProductOnShelfById(id);
        return productOnShelf.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductOnShelf> createProductOnShelf(@RequestBody ProductOnShelf productOnShelf) {
        ProductOnShelf savedProductOnShelf = productOnShelfService.saveProductOnShelf(productOnShelf);
        return new ResponseEntity<>(savedProductOnShelf, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductOnShelf> updateProductOnShelf(@PathVariable Long id, @RequestBody ProductOnShelf productOnShelf) {
        if (!productOnShelfService.getProductOnShelfById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productOnShelf.setId(id);
        ProductOnShelf updatedProductOnShelf = productOnShelfService.saveProductOnShelf(productOnShelf);
        return ResponseEntity.ok(updatedProductOnShelf);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductOnShelf(@PathVariable Long id) {
        if (!productOnShelfService.getProductOnShelfById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productOnShelfService.deleteProductOnShelf(id);
        return ResponseEntity.noContent().build();
    }
}
