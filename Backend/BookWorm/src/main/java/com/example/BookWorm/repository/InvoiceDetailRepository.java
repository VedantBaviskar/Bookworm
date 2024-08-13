package com.example.BookWorm.repository;

import com.example.BookWorm.models.InvoiceDetail;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
	List<InvoiceDetail> findByInvoice_InvoiceId(Long invoiceId);

    @Transactional
    @Modifying
    @Query("DELETE FROM InvoiceDetail id WHERE id.invoice.invoiceId = :invoiceId")
    void deleteByInvoiceId(Long invoiceId);
	
}
