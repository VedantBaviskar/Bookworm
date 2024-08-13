package com.example.BookWorm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookWorm.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
