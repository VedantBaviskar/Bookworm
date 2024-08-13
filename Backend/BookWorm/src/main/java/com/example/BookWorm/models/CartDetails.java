package com.example.BookWorm.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
public class CartDetails {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProductGenreId",length=10,nullable=false)
    int ctid;
	 @ManyToOne
	    @JoinColumn(name = "Cart_Id")
	 @JsonBackReference
	 CartMaster cid;
	 @ManyToOne
	    @JoinColumn(name = "ProductId")
//	 @JsonManagedReference
	    Product product;
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ctid) {
		this.ctid = ctid;
	}
	public CartMaster getCid() {
		return cid;
	}
	public void setCid(CartMaster cid) {
		this.cid = cid;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
