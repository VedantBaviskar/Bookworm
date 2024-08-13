package com.example.BookWorm.service;

import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.repository.CartDetailsRepository;
import com.example.BookWorm.repository.CartMasterRepository;
import com.example.BookWorm.repository.CustomerMasterRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartMasterService {

    @Autowired
    private CartMasterRepository cartMasterRepository;
    @Autowired
    private CustomerMasterRepository customerMasterRepository;
    @Autowired
    private CartDetailsService cartDetailsService;
    @Autowired
    private CartDetailsRepository cartDetailsRepository;
    @Transactional
    public void deleteCartByCustomerId(Long customerId) {
        Optional<CustomerMaster> customerOpt = customerMasterRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            CartMaster cartMaster = customerOpt.get().getCart();
            if (cartMaster != null) {
                // Delete CartDetails associated with CartMaster
                cartDetailsService.deleteCartDetails(cartMaster.getId());

                // Delete CartMaster
                cartMasterRepository.deleteById(cartMaster.getId());
            }
        }
    }
    public List<CartMaster> getAllCarts() {
        return cartMasterRepository.findAll();
    }


    
//    @Transactional
//    public void deleteCartByCustomerId(Long customerId) {
//        // Find CartMasters by Customer ID
//        List<CartMaster> carts = cartMasterRepository.findByCustomerId(customerId);
//
//        // Delete CartDetails for each CartMaster
//        for (CartMaster cart : carts) {
//            cartDetailsService.deleteByCartId(cart.getId());
//        }
//
//        // Delete CartMasters
//        for (CartMaster cart : carts) {
//            cartMasterRepository.deleteById(cart.getId());
//        }
//    }
//    public CartMaster getCartByCustomerId(Long customerId) {
//        // Check if the method is appropriate based on your CartMaster entity
//        return cartMasterRepository.findByCustomerId(customerId);
//    }   

    public Optional<CartMaster> getCartById(int id) {
        return cartMasterRepository.findById(id);
    }

    @Transactional
    public CartMaster saveCart(CartMaster cartMaster,long id) {
        // Save the CartMaster entity
        CartMaster createdCart = cartMasterRepository.save(cartMaster);

        // Find the customer by ID
        CustomerMaster customer = customerMasterRepository.findById(id).orElse(null);
        if (customer != null) {
            // Update the customer with the new cart
            customer.setCart(createdCart);
            customerMasterRepository.save(customer);
        }
        
        return createdCart;
    }

    public void deleteCart(int id) {
        cartMasterRepository.deleteById(id);
    }

    public CartMaster updateCart(int id, CartMaster cartDetails) {
        return cartMasterRepository.findById(id).map(cart -> {
            cart.setNoofbooks(cartDetails.getNoofbooks());
            cart.setCost(cartDetails.getCost());
            return cartMasterRepository.save(cart);
        }).orElseGet(() -> {
            cartDetails.setId(id);
            return cartMasterRepository.save(cartDetails);
        });
    }
    
}
