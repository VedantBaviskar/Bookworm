package com.example.BookWorm.repository;

import com.example.BookWorm.models.Invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	 List<Invoice> findByCustomer_CustomerId(Long customerId);
}
