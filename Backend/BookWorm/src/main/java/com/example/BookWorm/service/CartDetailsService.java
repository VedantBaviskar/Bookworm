package com.example.BookWorm.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BookWorm.models.CartDetails;
import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.CustomerMaster;
import com.example.BookWorm.models.Product;
import com.example.BookWorm.repository.CartDetailsRepository;
import com.example.BookWorm.repository.CartMasterRepository;
import com.example.BookWorm.repository.CustomerMasterRepository;
import com.example.BookWorm.repository.ProductRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailsService {

    @Autowired
    private CartDetailsRepository cartDetailsRepository;
   
    @Autowired
    private CartMasterRepository cartMasterRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerMasterRepository customerMasterRepository;
    public List<CartDetails> getAllCartDetails() {
        return cartDetailsRepository.findAll();
    }
    
    @Transactional
    public void deleteByCartId(int cartId) {
        // Delete CartDetails by CartMaster ID
        List<CartDetails> cartDetailsList = cartDetailsRepository.findByCidId(cartId);
        cartDetailsRepository.deleteAll(cartDetailsList);
    }
    public Optional<CartDetails> getCartDetailsById(int id) {
        return cartDetailsRepository.findById(id);
    }
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public void clearCartDetailsAndResetCartMasterByCartId(int cartId) {
        // Fetch all CartDetails by cartId
        List<CartDetails> cartDetailsList = cartDetailsRepository.findByCidId(cartId);
        if (cartDetailsList.isEmpty()) {
            throw new IllegalArgumentException("No CartDetails found for CartId: " + cartId);
        }
        
        // Fetch the CartMaster by cartId
        Optional<CartMaster> cartMasterOpt = cartMasterRepository.findById(cartId);
        if (!cartMasterOpt.isPresent()) {
            throw new IllegalArgumentException("No CartMaster found for CartId: " + cartId);
        }

        // Delete all CartDetails
        cartDetailsRepository.deleteAll(cartDetailsList);
        
        // Update the CartMaster to reset noofbooks and cost
        CartMaster cartMaster = cartMasterOpt.get();
        cartMaster.setNoofbooks(0);
        cartMaster.setCost(0);
        cartMasterRepository.save(cartMaster);
    }
    public boolean isProductInCart(Long customerId, Long productId) {
        // Check if a CartMaster exists for the customer
        Optional<CustomerMaster> customerOpt = customerMasterRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            CartMaster cartMaster = customerOpt.get().getCart();
            if (cartMaster != null) {
                // Check if the product is in the cart
                return cartDetailsRepository.existsByCidAndProduct_ProductId(cartMaster, productId);
            }
        }
        return false;
    }
    public List<CartDetails> getCartDetailsByCustomerId(Long customerId) {
        // Fetch the customer to get the associated CartMaster
        Optional<CustomerMaster> customerOpt = customerMasterRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            CartMaster cartMaster = customerOpt.get().getCart();
            if (cartMaster != null) {
                // Return all CartDetails associated with the CartMaster
                return cartDetailsRepository.findByCid(cartMaster);
            }
        }
        return List.of(); // Return an empty list if the customer or cart is not found
    }
    @Transactional
    public CartDetails saveCartDetails(Long customerId, CartDetails cartDetails) {
        // Fetch the customer to get the associated CartMaster
        CustomerMaster customer = customerMasterRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        CartMaster cartMaster = customer.getCart(); // Get CartMaster from Customer
        Product product = productRepository.findById(cartDetails.getProduct().getProductId()).orElse(null);

        if (cartMaster != null && product != null) {
            cartDetails.setCid(cartMaster);
            cartDetails.setProduct(product);

            // Save CartDetails
            CartDetails savedCartDetails = cartDetailsRepository.save(cartDetails);

            // Update CartMaster
            updateCartMaster(cartMaster, product.getProductSpCost());

            return savedCartDetails;
        } else {
            // Handle case where entities do not exist
            throw new IllegalArgumentException("CartMaster or Product not found");
        }
    }
//    public boolean isProductInCart(Long customerId, Long productId) {
//        return cartDetailsRepository.findByCidAndProduct_ProductId(customerId, productId).isPresent();
//    }
    private void updateCartMaster(CartMaster cartMaster, double productSalesPrice) {
        // Update number of books
        cartMaster.setNoofbooks(cartMaster.getNoofbooks() + 1);

        // Update total cost
        cartMaster.setCost(cartMaster.getCost() + productSalesPrice);

        // Save updated CartMaster
        cartMasterRepository.save(cartMaster);
    }
    @Transactional
    public void deleteCartDetails(int id) {
        Optional<CartDetails> cartDetailsOpt = cartDetailsRepository.findById(id);
        if (cartDetailsOpt.isPresent()) {
            CartDetails cartDetails = cartDetailsOpt.get();
            CartMaster cartMaster = cartDetails.getCid();
            Product product = cartDetails.getProduct();

            if (cartMaster != null && product != null) {
                // Update CartMaster before deleting the CartDetails
                cartMaster.setNoofbooks(cartMaster.getNoofbooks() - 1);
                cartMaster.setCost(cartMaster.getCost() - product.getProductSpCost());

                // Save updated CartMaster
                cartMasterRepository.save(cartMaster);

                // Delete CartDetails
                cartDetailsRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("CartMaster or Product not found");
            }
        } else {
            throw new IllegalArgumentException("CartDetails not found");
        }
    }
}
