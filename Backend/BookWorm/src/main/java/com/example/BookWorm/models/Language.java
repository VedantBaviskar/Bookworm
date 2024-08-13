package com.example.BookWorm.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Language {
	public Language() {}
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Type-Id",length=10,nullable=false)
	private int id;
	
	@Column(name="Language",length=50,nullable=false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Product> ob;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Language(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
