package com.example.BookWorm.repository;



import com.example.BookWorm.models.LibraryPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryPackageRepository extends JpaRepository<LibraryPackage, Integer> {
}
