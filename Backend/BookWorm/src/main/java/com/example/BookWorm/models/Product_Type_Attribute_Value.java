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
public class Product_Type_Attribute_Value {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProductTypeAttributeId",length=10,nullable=false)
    int ptav;
	@ManyToOne
    @JoinColumn(name = "AttributeId")
     @JsonManagedReference
    AttributeMaster am1;
	@ManyToOne
    @JoinColumn(name = "ProductId")
     @JsonManagedReference
    Product product1;
	@Column(name = "ProductAttributevalue", nullable = false)
	@JsonManagedReference
	private String value;
}
