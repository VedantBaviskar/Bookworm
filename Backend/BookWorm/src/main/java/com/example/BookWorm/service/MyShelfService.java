package com.example.BookWorm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.CartDetails;
import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.MyShelf;
import com.example.BookWorm.models.ProductOnShelf;
import com.example.BookWorm.repository.CartDetailsRepository;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.MyShelfRepository;
import com.example.BookWorm.repository.ProductOnShelfRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MyShelfService {

    @Autowired
    private MyShelfRepository myShelfRepository;

    @Autowired
    private CustomerMasterRepository customerMasterRepository;
    
    @Transactional
    public void moveProductsToShelf(Long customerId) {
        // Fetch the customer by ID
        CustomerMaster customer = customerMasterRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        // Get the shelf and cart from the customer
        MyShelf myShelf = customer.getShelf();
        int cartId =  customer.getCart().getId();

        if (myShelf == null) {
            throw new RuntimeException("Shelf not found for customer id: " + customerId);
        }

        // Fetch the cart details by Cart_Id
        List<CartDetails> cartDetailsList = cartDetailsRepository.findByCid(customer.getCart());

        // Iterate over cart details and move products to shelf
        for (CartDetails cartDetails : cartDetailsList) {
            ProductOnShelf productOnShelf = new ProductOnShelf();
            productOnShelf.setShelf(myShelf);
            productOnShelf.setProduct(cartDetails.getProduct());
            productOnShelf.setBasePrice(cartDetails.getProduct().getProductSpCost());
            productOnShelf.setTranType("B"); // assuming 'B' means 'Bought'
            productOnShelf.setRentNoOfDays(null); // assuming no rental period

            productOnShelfRepository.save(productOnShelf);

            // Increment number of books in MyShelf
            myShelf.setNoofbooks(myShelf.getNoofbooks() + 1);
        }

        // Save the updated MyShelf
        myShelfRepository.save(myShelf);

        // Optionally, you can remove the products from CartDetails if needed
//        cartDetailsRepository.deleteAll(cartDetailsList);
    }
    
    
    public List<MyShelf> getAllMyShelves() {
        return myShelfRepository.findAll();
    }
       
    public Optional<MyShelf> getMyShelfById(int id) {
        return myShelfRepository.findById(id);
    }

    public MyShelf saveMyShelf(MyShelf myShelf) {
        return myShelfRepository.save(myShelf);
    }

    public void deleteMyShelf(int id) {
        myShelfRepository.deleteById(id);
    }
    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @Autowired
    private ProductOnShelfRepository productOnShelfRepository;
   }
