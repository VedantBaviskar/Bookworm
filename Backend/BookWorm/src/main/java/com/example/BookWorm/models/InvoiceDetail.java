package com.example.BookWorm.models;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invdtl_id")
    private Long invDtlId;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "tran_type", length = 1, nullable = false)
    private String tranType;

    @Column(name = "rent_no_of_days")
    private Integer rentNoOfDays;

    // Getters and Setters

    public Long getInvDtlId() {
        return invDtlId;
    }

    public void setInvDtlId(Long invDtlId) {
        this.invDtlId = invDtlId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
