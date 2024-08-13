package com.example.BookWorm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookWorm.models.Product;
import com.example.BookWorm.models.ProductGenre;
import com.example.BookWorm.repository.ProductGenreRepository;

@Service
public class ProductGenreService {

    @Autowired
    private ProductGenreRepository productGenreRepository;

    public List<ProductGenre> getAllProductGenres() {
        List<ProductGenre> productGenres = productGenreRepository.findAll();
        productGenres.forEach(pg -> System.out.println("Retrieved ProductGenre: " + pg));
        return productGenres;
    }
    public List<ProductGenre> getProductGenresByProductTypeId(int typeId) {
        return productGenreRepository.findByProductTypeId(typeId);
    }
   
    public Optional<ProductGenre> getProductGenreById(int id) {
        Optional<ProductGenre> productGenre = productGenreRepository.findById(id);
        productGenre.ifPresent(pg -> System.out.println("Retrieved ProductGenre: " + pg));
        return productGenre;
    }

    public List<ProductGenre> getProductGenresByProductId(Long productId) {
        return productGenreRepository.findByProductId(productId);
    }
    public List<ProductGenre> getProductGenresByTypeIdAndGenreId(int typeId, int genreId) {
        return productGenreRepository.findByProductTypeIdAndGenreId(typeId, genreId);
    }
}
