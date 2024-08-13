package com.example.BookWorm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BookWorm.models.AttributeMaster;

public interface AttributeMasterRepository extends JpaRepository<AttributeMaster, Integer> {
}
