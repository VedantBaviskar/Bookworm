package com.example.BookWorm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookWorm.models.ProdBeneficiaryMaster;
import com.example.BookWorm.models.Product;

public interface ProductBeneficiaryMaster  extends JpaRepository<ProdBeneficiaryMaster, Long> {
    Set<ProdBeneficiaryMaster> findByProduct_ProductId(Long productId);
//    List<ProdBeneficiaryMaster> findByProduct(Product product);
    List<ProdBeneficiaryMaster> findByProduct(Product product);    
}