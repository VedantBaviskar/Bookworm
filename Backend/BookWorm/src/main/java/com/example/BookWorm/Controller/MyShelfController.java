package com.example.BookWorm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.LibraryPackage;
import com.example.BookWorm.models.MyShelf;
import com.example.BookWorm.models.Product;
import com.example.BookWorm.service.MyShelfService;
import com.example.BookWorm.models.ProductOnShelf;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.ProductOnShelfRepository;
import com.example.BookWorm.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/my-shelves")
@CrossOrigin(origins = "*")  // Enable CORS for all origins
public class MyShelfController {

    @Autowired
    private MyShelfService myShelfService;
    @PostMapping("/move/{customerId}")
    public void moveProductsToShelf(@PathVariable Long customerId) {
    	myShelfService.moveProductsToShelf( customerId);
    }

    @GetMapping
    public List<MyShelf> getAllMyShelves() {
        return myShelfService.getAllMyShelves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyShelf> getMyShelfById(@PathVariable int id) {
        Optional<MyShelf> myShelf = myShelfService.getMyShelfById(id);
        return myShelf.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<MyShelf> createMyShelf(@RequestBody MyShelf myShelf) {
        MyShelf savedMyShelf = myShelfService.saveMyShelf(myShelf);
        return new ResponseEntity<>(savedMyShelf, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MyShelf> updateMyShelf(@PathVariable int id, @RequestBody MyShelf myShelf) {
        if (!myShelfService.getMyShelfById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        myShelf.setId(id);
        MyShelf updatedMyShelf = myShelfService.saveMyShelf(myShelf);
        return ResponseEntity.ok(updatedMyShelf);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMyShelf(@PathVariable int id) {
        if (!myShelfService.getMyShelfById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        myShelfService.deleteMyShelf(id);
        return ResponseEntity.noContent().build();
    }
    @Autowired
    private CustomerMasterRepository customerMasterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOnShelfRepository productOnShelfRepository;
    @PostMapping("/addProduct/{customerId}/{productId}")
    public ResponseEntity<String> addProductToShelf(
            @PathVariable Long customerId,
            @PathVariable Long productId) {
        
        // Fetch the customer
        CustomerMaster customer = customerMasterRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get the shelf associated with the customer
        MyShelf shelf = customer.getShelf();
        if (shelf == null) {
            return ResponseEntity.badRequest().body("Customer has no associated shelf.");
        }

        // Check if the customer has any products on the shelf
//        List<ProductOnShelf> productsOnShelf = productOnShelfRepository.findByShelf(shelf);
//        if (productsOnShelf.isEmpty()) {
//            return ResponseEntity.badRequest().body("Customer has no products on the shelf.");
//        }

        // Fetch the library package associated with the customer
        LibraryPackage libraryPackage = customer.getLibraryPackage();
        if (libraryPackage == null) {
            return ResponseEntity.badRequest().body("Customer has no associated library package.");
        }

        // Number of books allowed by the library package
        int allowedBooks = libraryPackage.getNoofbooksallowed();

        // Count the number of books with tranType = "L" on the shelf
        long currentLentBooksCount = productOnShelfRepository.countByShelfAndTranType(shelf, "L");

        // Check if adding this book exceeds the limit
        if (currentLentBooksCount >= allowedBooks) {
            return ResponseEntity.badRequest().body("Cannot add more books. Limit reached for lent books.");
        }

        // Check if the product already exists on the shelf with tranType = "L"
        boolean exists = productOnShelfRepository.existsByShelfAndProductAndTranType(shelf, product, "L");
        if (exists) {
            return ResponseEntity.badRequest().body("Product already exists on the shelf with tranType 'L'.");
        }

        // Create a new ProductOnShelf entry
        ProductOnShelf productOnShelf = new ProductOnShelf();
        productOnShelf.setShelf(shelf);
        productOnShelf.setProduct(product);
        productOnShelf.setBasePrice(product.getProductBaseprice()); // Set the base price
        productOnShelf.setTranType("L"); // 'L' for Lent
        productOnShelf.setRentNoOfDays(null); // or set the number of days if required

        // Save the ProductOnShelf entity
        productOnShelfRepository.save(productOnShelf);
        shelf.setNoofbooks(shelf.getNoofbooks()+1);
        myShelfService.saveMyShelf(shelf);
        return ResponseEntity.ok("Product added to shelf successfully.");
    
    }



}
