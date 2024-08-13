package com.example.BookWorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class MyShelf {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Shelf_Id",length=10,nullable=false)
	private int id;
	public MyShelf(int id, int noofbooks) {
		super();
		this.id = id;
		this.noofbooks = noofbooks;
	}
	public MyShelf() {
		
	}
	@Column(name="Number_Of_Books",length=50,nullable=false)
	int noofbooks;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNoofbooks() {
		return noofbooks;
	}
	public void setNoofbooks(int noofbooks) {
		this.noofbooks = noofbooks;
	}
}
