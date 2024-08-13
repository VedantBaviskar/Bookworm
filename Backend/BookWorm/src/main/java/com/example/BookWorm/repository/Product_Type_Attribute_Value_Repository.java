package com.example.BookWorm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BookWorm.models.Product_Type_Attribute_Value;

public interface Product_Type_Attribute_Value_Repository extends JpaRepository<Product_Type_Attribute_Value,Integer>{

    @Query("SELECT ptav FROM Product_Type_Attribute_Value ptav WHERE ptav.product1.id = :productId")
    List<Product_Type_Attribute_Value> findByProductId(@Param("productId") Long productId);
}
