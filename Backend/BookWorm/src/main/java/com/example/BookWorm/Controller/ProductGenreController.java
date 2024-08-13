package com.example.BookWorm.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BookWorm.models.Product;
import com.example.BookWorm.models.ProductGenre;
import com.example.BookWorm.service.ProductGenreService;

@RestController
@RequestMapping("/api/product-genres")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductGenreController {

    @Autowired
    private ProductGenreService productGenreService;

    @GetMapping
    public ResponseEntity<List<ProductGenre>> getAllProductGenres() {
        List<ProductGenre> productGenres = productGenreService.getAllProductGenres();
        return new ResponseEntity<>(productGenres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGenre> getProductGenreById(@PathVariable int id) {
        return productGenreService.getProductGenreById(id)
                .map(productGenre -> new ResponseEntity<>(productGenre, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductGenre>> getProductGenresByProductId(@PathVariable Long productId) {
        List<ProductGenre> productGenres = productGenreService.getProductGenresByProductId(productId);
        if (productGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productGenres, HttpStatus.OK);
    }
    @GetMapping("/product-type/{typeId}")
    public ResponseEntity<List<ProductGenre>> getProductGenresByProductTypeId(@PathVariable int typeId) {
        List<ProductGenre> productGenres = productGenreService.getProductGenresByProductTypeId(typeId);
        if (productGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productGenres, HttpStatus.OK);
    }
    @GetMapping("/products/type/{typeId}/genre/{genreId}")
    public ResponseEntity<List<ProductGenre>> getProductGenresByTypeAndGenre(
            @PathVariable int typeId, 
            @PathVariable int genreId) {
        List<ProductGenre> productGenres = productGenreService.getProductGenresByTypeIdAndGenreId(typeId, genreId);
        if (productGenres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productGenres, HttpStatus.OK);
    }

}
