package com.example.BookWorm.service;



import com.example.BookWorm.models.Product;
import com.example.BookWorm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
    public List<Product> getProductsByProductTypeId(int typeId) {
        return productRepository.findByProductTypeId(typeId);
    }
    public Product updateProduct(Long productId, Product productDetails) {
        return productRepository.findById(productId).map(product -> {
            product.setProductName(productDetails.getProductName());
            product.setProductEnglishName(productDetails.getProductEnglishName());
            product.setProductType(productDetails.getProductType());
            product.setProductBaseprice(productDetails.getProductBaseprice());
            product.setProductSpCost(productDetails.getProductSpCost());
            product.setProductOfferprice(productDetails.getProductOfferprice());
            product.setProductOffPriceExpirydate(productDetails.getProductOffPriceExpirydate());
            product.setProductDescriptionShort(productDetails.getProductDescriptionShort());
            product.setProductDescriptionLong(productDetails.getProductDescriptionLong());
            product.setProductIsbn(productDetails.getProductIsbn());
            product.setProductAuthor(productDetails.getProductAuthor());
            product.setProductPublisher(productDetails.getProductPublisher());
            product.setProductLang(productDetails.getProductLang());
            product.setIsRentable(productDetails.getIsRentable());
            product.setIsLibrary(productDetails.getIsLibrary());
            product.setRentPerDay(productDetails.getRentPerDay());
            product.setMinRentDays(productDetails.getMinRentDays());
            return productRepository.save(product);
        }).orElseGet(() -> {
            productDetails.setProductId(productId);
            return productRepository.save(productDetails);
        });
        
    }
  
}
