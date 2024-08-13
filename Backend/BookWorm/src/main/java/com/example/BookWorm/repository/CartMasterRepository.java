package com.example.BookWorm.repository;

import com.example.BookWorm.models.CartMaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMasterRepository extends JpaRepository<CartMaster, Integer> {
//	CartMaster findByCustomerId(Long customerId);
//	 List<CartMaster> findByCustomerId(Long customerId);
//	 Optional<CartMaster> findByCustomerId(Long customerId);
	
}
