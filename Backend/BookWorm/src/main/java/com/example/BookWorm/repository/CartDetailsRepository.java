package com.example.BookWorm.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.BookWorm.models.CartDetails;
import com.example.BookWorm.models.CartMaster;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {
	List<CartDetails> findByCid_Id(int cartId);
//	Optional<CartDetails> findByCidAndProduct_ProductId(Long customerId, Long productId);
	 boolean existsByCidAndProduct_ProductId(CartMaster cartMaster, Long productId);
	 List<CartDetails> findByCid(CartMaster cartMaster);
//	  List<CartDetails> findByCartCartId(int cartId);
//	 List<CartDetails> findByCustomerId(Long customerId);
//	 List<CartDetails> findByCartId(int cartId);	
	 List<CartDetails> findByCidId(int cartId); //
	// List<CartDetails> findByCart_CartId(int cartId);
	 
	 
}

