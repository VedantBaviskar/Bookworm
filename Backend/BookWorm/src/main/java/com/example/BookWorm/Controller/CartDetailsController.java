package com.example.BookWorm.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BookWorm.models.CartDetails;
import com.example.BookWorm.models.Product;
import com.example.BookWorm.service.CartDetailsService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartDetails")
@CrossOrigin(origins = "http://localhost:3000")  // Enable CORS for all origins
public class CartDetailsController {

    @Autowired
    private CartDetailsService cartDetailsService;

    @GetMapping
    public List<CartDetails> getAllCartDetails() {
        return cartDetailsService.getAllCartDetails();
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CartDetails>> getCartDetailsByCustomerId(
            @PathVariable Long customerId) {
        List<CartDetails> cartDetails = cartDetailsService.getCartDetailsByCustomerId(customerId);
        return ResponseEntity.ok(cartDetails);
    }

    @GetMapping("/check/{customerId}/{productId}")
    public ResponseEntity<Map<String, Boolean>> checkProductInCart(
            @PathVariable Long customerId,
            @PathVariable Long productId) {
        boolean exists = cartDetailsService.isProductInCart(customerId, productId);
        Map<String, Boolean> response = new HashMap<String,Boolean>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Void> clearCartDetailsAndResetCartMasterByCartId(@PathVariable int cartId) {
        try {
            cartDetailsService.clearCartDetailsAndResetCartMasterByCartId(cartId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{id}")
    public Optional<CartDetails> getCartDetailsById(@PathVariable int id) {
        return cartDetailsService.getCartDetailsById(id);
    }


    @PostMapping("/{customerId}")
    public ResponseEntity<CartDetails> createCartDetails(
            @PathVariable Long customerId,
            @RequestBody CartDetails cartDetails) {
        try {
            CartDetails savedCartDetails = cartDetailsService.saveCartDetails(customerId, cartDetails);
            return ResponseEntity.ok(savedCartDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> product = cartDetailsService.getProductById(productId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PutMapping("/{id}")
//    public CartDetails updateCartDetails(@PathVariable int id, @RequestBody CartDetails cartDetails) {
//        cartDetails.setCtid(id);
//        return cartDetailsService.saveCartDetails(cartDetails);
//    }

    @DeleteMapping("/{id}")
    public void deleteCartDetails(@PathVariable int id) {
        cartDetailsService.deleteCartDetails(id);
    }
}
