package com.example.BookWorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "beneficiary_master")
public class BeneficiaryMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ben_id")
    private Long benId;

    @Column(name = "ben_name", nullable = false)
    private String benName;

    @Column(name = "ben_email_id")
    private String benEmailId;

    @Column(name = "ben_contact_no")
    private String benContactNo;

    @Column(name = "ben_bank_name")
    private String benBankName;

    @Column(name = "ben_bank_branch")
    private String benBankBranch;

    @Column(name = "ben_ifsc")
    private String benIFSC;

    @Column(name = "ben_acc_no")
    private String benAccNo;

    @Column(name = "ben_acc_type")
    private String benAccType;

    @Column(name = "ben_pan")
    private String benPAN;

    // Getters and Setters

    public Long getBenId() {
        return benId;
    }

    public void setBenId(Long benId) {
        this.benId = benId;
    }

    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public String getBenEmailId() {
        return benEmailId;
    }

    public void setBenEmailId(String benEmailId) {
        this.benEmailId = benEmailId;
    }

    public String getBenContactNo() {
        return benContactNo;
    }

    public void setBenContactNo(String benContactNo) {
        this.benContactNo = benContactNo;
    }

    public String getBenBankName() {
        return benBankName;
    }

    public void setBenBankName(String benBankName) {
        this.benBankName = benBankName;
    }

    public String getBenBankBranch() {
        return benBankBranch;
    }

    public void setBenBankBranch(String benBankBranch) {
        this.benBankBranch = benBankBranch;
    }

    public String getBenIFSC() {
        return benIFSC;
    }

    public void setBenIFSC(String benIFSC) {
        this.benIFSC = benIFSC;
    }

    public String getBenAccNo() {
        return benAccNo;
    }

    public void setBenAccNo(String benAccNo) {
        this.benAccNo = benAccNo;
    }

    public String getBenAccType() {
        return benAccType;
    }

    public void setBenAccType(String benAccType) {
        this.benAccType = benAccType;
    }

    public String getBenPAN() {
        return benPAN;
    }

    public void setBenPAN(String benPAN) {
        this.benPAN = benPAN;
    }
}
