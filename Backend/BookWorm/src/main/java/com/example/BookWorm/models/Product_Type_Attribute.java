package com.example.BookWorm.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Product_Type_Attribute {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductTypeAttributeId", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "AttributeId")
   // @JsonBackReference
    private AttributeMaster am;

    @ManyToOne
    @JoinColumn(name = "ProductTypeId")
    //@JsonBackReference
    private ProductType pt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AttributeMaster getAm() {
		return am;
	}

	public void setAm(AttributeMaster am) {
		this.am = am;
	}

	public ProductType getPt() {
		return pt;
	}

	public void setPt(ProductType pt) {
		this.pt = pt;
	}
    
}
