package com.example.BookWorm.repository;



import com.example.BookWorm.models.BeneficiaryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryMasterRepository extends JpaRepository<BeneficiaryMaster, Long> {
}
