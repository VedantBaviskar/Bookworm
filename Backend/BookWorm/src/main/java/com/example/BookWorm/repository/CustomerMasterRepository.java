package com.example.BookWorm.repository;

import com.example.BookWorm.models.CartMaster;
import com.example.BookWorm.models.CustomerMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, Long> {
    // You can define custom query methods here if needed
	 Optional<CustomerMaster> findByCustomerEmailAndCustomerPassword(String email, String password);

	
}
