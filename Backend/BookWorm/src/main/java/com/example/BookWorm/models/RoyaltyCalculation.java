package com.example.BookWorm.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RoyaltyCalculation {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="RoyCalId", length=10, nullable=false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Invoice_id", referencedColumnName = "invoice_id")
    private Invoice invoice;

    public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public BeneficiaryMaster getBeneficiaryMaster() {
		return beneficiaryMaster;
	}

	public void setBeneficiaryMaster(BeneficiaryMaster beneficiaryMaster) {
		this.beneficiaryMaster = beneficiaryMaster;
	}

	public LocalDateTime getRoyaltyDate() {
		return royaltyDate;
	}

	public void setRoyaltyDate(LocalDateTime royaltyDate) {
		this.royaltyDate = royaltyDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getRoyaltyOnBasePrice() {
		return royaltyOnBasePrice;
	}

	public void setRoyaltyOnBasePrice(double royaltyOnBasePrice) {
		this.royaltyOnBasePrice = royaltyOnBasePrice;
	}

	public int getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "Ben_id", referencedColumnName = "ben_id")
	private BeneficiaryMaster beneficiaryMaster;

    @Column(name = "royalty_date")
    private LocalDateTime royaltyDate;

    @ManyToOne
    @JoinColumn(name = "Product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "TransactionType")
    private String transactionType;

    @Column(name = "sales_price")
    private double salesPrice;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "Royalty_On_base_price")
    private double royaltyOnBasePrice;

	public void setId(int id2) {
		// TODO Auto-generated method stub
		
	}
}
