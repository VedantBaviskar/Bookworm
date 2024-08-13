package com.example.BookWorm.Controller;

import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.MyShelf;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.LoginRequests;
import com.example.BookWorm.repository.CartMasterRepository;
import com.example.BookWorm.repository.MyShelfRepository;
import com.example.BookWorm.service.CustomerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000") 
public class CustomerMasterController {

    @Autowired
    private CustomerMasterService customerMasterService;

    @GetMapping
    public List<CustomerMaster> getAllCustomers() {
        return customerMasterService.getAllCustomers();
    }
    @PostMapping("/{customerId}/create-shelf")
    public void createShelfForCustomer(@PathVariable Long customerId) {
    	customerMasterService.createShelfIfNotExists(customerId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerMaster> getCustomerById(@PathVariable int id) {
        Optional<CustomerMaster> customer = customerMasterService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Autowired
    private CartMasterRepository cartMasterRepository;
    @Autowired
    private MyShelfRepository myShelfRepository;

    @PostMapping
    public CustomerMaster createCustomer(@RequestBody CustomerMaster customer) {
    	CartMaster cart = new CartMaster();
        cart.setNoofbooks(0);
        cart.setCost(0.0);
        CartMaster savedCart = cartMasterRepository.save(cart);

        // Create a new shelf with default values
        MyShelf shelf = new MyShelf();
        shelf.setNoofbooks(0);
        MyShelf savedShelf = myShelfRepository.save(shelf);
         customer.setCart(savedCart);
         customer.setShelf(savedShelf);
        return customerMasterService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerMaster> updateCustomer(@PathVariable int id, @RequestBody CustomerMaster customerDetails) {
        return ResponseEntity.ok(customerMasterService.updateCustomer(id, customerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerMasterService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<CustomerMaster> loginCustomer(@RequestBody LoginRequests loginRequest) {
        Optional<CustomerMaster> customer = customerMasterService.getCustomerByEmailAndPassword(loginRequest.getCustomerEmail(), loginRequest.getCustomerPassword());
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/{customerId}/cart")
    public ResponseEntity<CartMaster> createCartAndAssignToCustomer(@RequestBody CartMaster cart, @PathVariable Long customerId) {
        CartMaster createdCart = customerMasterService.createCartAndAssignToCustomer(cart, customerId);
        return ResponseEntity.ok(createdCart);
    }
    @PutMapping("/{customerId}/library/{libraryId}")
    public ResponseEntity<String> updateCustomerLibrary(
            @PathVariable Long customerId,
            @PathVariable Integer libraryId) {

        boolean updated = customerMasterService.updateCustomerLibrary(customerId, libraryId);

        if (updated) {
            return ResponseEntity.ok("Library package updated successfully.");
        } else {
            return ResponseEntity.status(400).body("Update failed. Please check the customer and library IDs.");
        }
    }
}
