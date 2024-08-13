package com.example.BookWorm.Controller;



import com.example.BookWorm.models.Language;
import com.example.BookWorm.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/getall")
    public ResponseEntity<List<Language>> getAllLanguages() {
        List<Language> languages = languageService.getAllLanguages();
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable int id) {
        Optional<Language> language = languageService.getLanguageById(id);
        return language.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
        Language savedLanguage = languageService.saveLanguage(language);
        return new ResponseEntity<>(savedLanguage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable int id, @RequestBody Language language) {
        if (!languageService.getLanguageById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        language.setId(id);
        Language updatedLanguage = languageService.saveLanguage(language);
        return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable int id) {
        if (!languageService.getLanguageById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}

