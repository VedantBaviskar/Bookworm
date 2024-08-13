package com.example.BookWorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class PublisherMaster {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Publisher_Id",length=10,nullable=false)
	private int Id;
	
	@Column(name="Publisher_Name",length=50,nullable=false)
	private String name;
	
	
	public PublisherMaster() {}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PublisherMaster(int id, String name) {
		Id = id;
		this.name = name;
	}
	
}
