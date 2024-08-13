package com.example.BookWorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookWorm.models.ProductType;



@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType,Integer> {

}
