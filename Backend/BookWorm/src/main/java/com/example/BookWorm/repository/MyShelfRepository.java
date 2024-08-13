package com.example.BookWorm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.BookWorm.models.MyShelf;

@Repository
public interface MyShelfRepository extends JpaRepository<MyShelf, Integer> {
//	 MyShelf findByCustomerId(Long customerId);
	 
	
}
