package com.example.BookWorm.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.LibraryPackage;
import com.example.BookWorm.repository.LibraryPackageRepository;

@Service
public class LibraryPackageService {

    @Autowired
    private LibraryPackageRepository libraryPackageRepository;

    public List<LibraryPackage> getAllPackages() {
        return libraryPackageRepository.findAll();
    }

    public Optional<LibraryPackage> getPackageById(int id) {
        return libraryPackageRepository.findById(id);
    }

    public LibraryPackage savePackage(LibraryPackage libraryPackage) {
        return libraryPackageRepository.save(libraryPackage);
    }

    public void deletePackage(int id) {
        libraryPackageRepository.deleteById(id);
    }
}
