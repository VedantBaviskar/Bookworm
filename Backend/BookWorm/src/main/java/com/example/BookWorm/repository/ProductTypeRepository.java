package com.example.BookWorm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BookWorm.models.Product_Type_Attribute;

@Repository
public interface ProductTypeRepository extends JpaRepository<Product_Type_Attribute, Integer> {
    @Query("SELECT pta FROM Product_Type_Attribute pta WHERE pta.pt.id = :productTypeId")
    List<Product_Type_Attribute> findByProductTypeId(@Param("productTypeId") int productTypeId);
}
