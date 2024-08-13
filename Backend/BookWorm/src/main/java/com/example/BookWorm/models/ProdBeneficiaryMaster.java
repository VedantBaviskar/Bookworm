package com.example.BookWorm.models;

import jakarta.persistence.*;

@Entity
@Table(name = "prod_beneficiary_master")
public class ProdBeneficiaryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_ben_id")
    private Long prodBenId;

    @ManyToOne
    @JoinColumn(name = "prod_ben_ben_id", referencedColumnName = "ben_id")
    private BeneficiaryMaster beneficiary;

    @ManyToOne
    @JoinColumn(name = "prod_ben_product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "prod_ben_percentage")
    private Double prodBenPercentage;

    public ProdBeneficiaryMaster() {}

    public ProdBeneficiaryMaster(Long prodBenId, BeneficiaryMaster beneficiary, Product product, Double prodBenPercentage) {
        this.prodBenId = prodBenId;
        this.beneficiary = beneficiary;
        this.product = product;
        this.prodBenPercentage = prodBenPercentage;
    }

    // Getters and Setters

    public Long getProdBenId() {
        return prodBenId;
    }

    public void setProdBenId(Long prodBenId) {
        this.prodBenId = prodBenId;
    }

    public BeneficiaryMaster getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryMaster beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getProdBenPercentage() {
        return prodBenPercentage;
    }

    public void setProdBenPercentage(Double prodBenPercentage) {
        this.prodBenPercentage = prodBenPercentage;
    }
}
