package com.example.BookWorm.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ProductGenre {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProductGenreId",length=10,nullable=false)
    int gid;
	@Override
	public String toString() {
	    return "ProductGenre{" +
	            "product_genre_id=" + gid +
	            ", genre=" + genre +
	            ", product=" + product +
	            '}';
	}

	 @ManyToOne
	    @JoinColumn(name = "ProductId")
	 @JsonManagedReference
	    Product product;
	 
	 
	  @ManyToOne
	    @JoinColumn(name = "Genre_Id")
	  @JsonManagedReference
	    Genre genre;
}
