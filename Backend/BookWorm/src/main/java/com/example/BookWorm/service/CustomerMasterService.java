package com.example.BookWorm.service;

import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.LibraryPackage;
import com.example.BookWorm.repository.CartMasterRepository;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.LibraryPackageRepository;
import com.example.BookWorm.repository.MyShelfRepository;
import com.example.BookWorm.models.MyShelf;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerMasterService {
	 @Autowired
	    private LibraryPackageRepository libraryPackageRepository;

	    public boolean updateCustomerLibrary(Long customerId, Integer libraryId) {
	        // Check if customer exists
	        if (!customerMasterRepository.existsById(customerId)) {
	            return false; // Customer not found
	        }

	        // Check if library package exists
	        if (!libraryPackageRepository.existsById(libraryId)) {
	            return false; // Library package not found
	        }

	        // Find customer and update the library package
	        CustomerMaster customer = customerMasterRepository.findById(customerId).orElse(null);
	        if (customer != null) {
	            LibraryPackage libraryPackage = libraryPackageRepository.findById(libraryId).orElse(null);
	            if (libraryPackage != null) {
	                customer.setLibraryPackage(libraryPackage);
	                LocalDate expiryDate = LocalDate.now().plusDays(libraryPackage.getDays());
	                customer.setRegistrationDate(expiryDate); //
	                customerMasterRepository.save(customer);
	                return true; // Successfully updated
	            }
	        }
	        return false; // Update failed
	    }

    @Autowired
    private CustomerMasterRepository customerMasterRepository;
    @Autowired
    private CartMasterRepository cartMasterRepository;
    @Autowired
    private MyShelfRepository myShelfRepository;

    public void createShelfIfNotExists(Long customerId) {
        Optional<CustomerMaster> customerOpt = customerMasterRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            CustomerMaster customer = customerOpt.get();

            if (customer.getShelf() == null) {
                MyShelf newShelf = new MyShelf();
                newShelf.setNoofbooks(0);
                myShelfRepository.save(newShelf);

                customer.setShelf(newShelf);
                customerMasterRepository.save(customer);
            }
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }
    public List<CustomerMaster> getAllCustomers() {
        return customerMasterRepository.findAll();
    }

    public Optional<CustomerMaster> getCustomerById(long id) {
        return customerMasterRepository.findById(id);
    }
    public Optional<CustomerMaster> getCustomerById(Long customerId) {
        return customerMasterRepository.findById(customerId);
    }
    public CustomerMaster saveCustomer(CustomerMaster customer) {
       
    	return customerMasterRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        customerMasterRepository.deleteById(id);
    }

    public CustomerMaster updateCustomer(long id, CustomerMaster customerDetails) {
        return customerMasterRepository.findById(id).map(customer -> {
            customer.setCustomerName(customerDetails.getCustomerName());
            customer.setCustomerEmail(customerDetails.getCustomerEmail());
            customer.setCustomerPassword(customerDetails.getCustomerPassword());
            customer.setDob(customerDetails.getDob());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setPan(customerDetails.getPan());
            customer.setCart(customerDetails.getCart());
            customer.setLibraryPackage(customerDetails.getLibraryPackage());
            return customerMasterRepository.save(customer);
        }).orElseGet(() -> {
            customerDetails.setCustomerId(id);
            return customerMasterRepository.save(customerDetails);
        });
    }
    public Optional<CustomerMaster> getCustomerByEmailAndPassword(String email, String password) {
        return customerMasterRepository.findByCustomerEmailAndCustomerPassword(email, password);
    }
    @Transactional
    public CartMaster createCartAndAssignToCustomer(CartMaster cart, Long customerId) throws RuntimeException {
        CartMaster savedCart = cartMasterRepository.save(cart);
        CustomerMaster customer = customerMasterRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setCart(savedCart);
        customerMasterRepository.save(customer);
        return savedCart;
    }
}
