package com.example.BookWorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "product_on_shelf")
public class ProductOnShelf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use the appropriate strategy for your use case
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shelf_id", referencedColumnName = "Shelf_Id", nullable = false)
    private MyShelf shelf;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "tran_type", nullable = false)
    private String tranType;

    @Column(name = "rent_no_of_days")
    private Integer rentNoOfDays;

    // Constructors, getters, setters, equals, and hashCode methods

    public ProductOnShelf() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MyShelf getShelf() {
        return shelf;
    }

    public void setShelf(MyShelf shelf) {
        this.shelf = shelf;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public Integer getRentNoOfDays() {
        return rentNoOfDays;
    }

    public void setRentNoOfDays(Integer rentNoOfDays) {
        this.rentNoOfDays = rentNoOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOnShelf that = (ProductOnShelf) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
