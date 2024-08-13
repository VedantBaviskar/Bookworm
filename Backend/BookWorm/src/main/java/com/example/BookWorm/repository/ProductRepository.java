package com.example.BookWorm.repository;



import com.example.BookWorm.models.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p JOIN FETCH p.productType WHERE p.productType.id = :typeId")
	List<Product> findByProductTypeId(@Param("typeId") int typeId);
	 Optional<Product> findByProductId(Long productId);
	 
}
