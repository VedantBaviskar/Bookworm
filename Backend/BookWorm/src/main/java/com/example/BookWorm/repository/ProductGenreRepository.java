package com.example.BookWorm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BookWorm.models.ProductGenre;
@Repository
public interface ProductGenreRepository extends JpaRepository<ProductGenre,Integer> {
	@Query("SELECT pg FROM ProductGenre pg WHERE pg.product.productId = :productId")
    List<ProductGenre> findByProductId(@Param("productId") Long productId);
	@Query("SELECT pg FROM ProductGenre pg WHERE pg.product.productType.id = :typeId")
    List<ProductGenre> findByProductTypeId(@Param("typeId") int typeId);
	@Query("SELECT pg FROM ProductGenre pg WHERE pg.product.productType.id = :typeId AND pg.genre.id = :genreId")
	List<ProductGenre> findByProductTypeIdAndGenreId(@Param("typeId") int typeId, @Param("genreId") int genreId);

	}
