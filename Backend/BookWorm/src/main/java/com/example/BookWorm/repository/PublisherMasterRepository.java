package com.example.BookWorm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BookWorm.models.PublisherMaster;

public interface PublisherMasterRepository extends JpaRepository<PublisherMaster, Integer> {
    // You can define custom query methods here if needed
}
