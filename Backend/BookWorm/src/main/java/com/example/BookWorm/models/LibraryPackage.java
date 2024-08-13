package com.example.BookWorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LibraryPackage {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	
	@Column(name="Library_Id",length=10,nullable=false)
	private int id;
	
	@Column(name="Package_Name",length=50,nullable=false)
	private String name;
	
	@Column(name="Package_Expiry_Days",length=50,nullable=false)
	private int days;
	
	
	@Column(name="Number_of_Books_Allowed",length=50,nullable=false)
	private int noofbooksallowed;
	
	@Column(name="Cost",length=50,nullable=false)
	private double cost;
	
	
	public int getId() {
		return id;
	}
	public LibraryPackage(int id, String name, int days, int noofbooksallowed, double cost) {
		super();
		this.id = id;
		this.name = name;
		this.days = days;
		this.noofbooksallowed = noofbooksallowed;
		this.cost = cost;
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
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getNoofbooksallowed() {
		return noofbooksallowed;
	}
	public void setNoofbooksallowed(int noofbooksallowed) {
		this.noofbooksallowed = noofbooksallowed;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public LibraryPackage() {}
}
