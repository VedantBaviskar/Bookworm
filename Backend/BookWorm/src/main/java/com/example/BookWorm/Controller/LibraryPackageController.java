package com.example.BookWorm.Controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookWorm.models.LibraryPackage;
import com.example.BookWorm.service.LibraryPackageService;

@RestController
@RequestMapping("/api/library/packages")
@CrossOrigin(origins = "*")
public class LibraryPackageController {

    @Autowired
    private LibraryPackageService libraryPackageService;

    @GetMapping
    public ResponseEntity<List<LibraryPackage>> getAllPackages() {
        List<LibraryPackage> packages = libraryPackageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryPackage> getPackageById(@PathVariable int id) {
        Optional<LibraryPackage> packageData = libraryPackageService.getPackageById(id);
        return packageData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LibraryPackage> createPackage(@RequestBody LibraryPackage libraryPackage) {
        LibraryPackage savedPackage = libraryPackageService.savePackage(libraryPackage);
        return ResponseEntity.ok(savedPackage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryPackage> updatePackage(@PathVariable int id, @RequestBody LibraryPackage libraryPackage) {
        Optional<LibraryPackage> packageData = libraryPackageService.getPackageById(id);
        if (packageData.isPresent()) {
            LibraryPackage existingPackage = packageData.get();
            existingPackage.setName(libraryPackage.getName());
            existingPackage.setDays(libraryPackage.getDays());
            existingPackage.setNoofbooksallowed(libraryPackage.getNoofbooksallowed());
            existingPackage.setCost(libraryPackage.getCost());
            LibraryPackage updatedPackage = libraryPackageService.savePackage(existingPackage);
            return ResponseEntity.ok(updatedPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable int id) {
        libraryPackageService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }
}
