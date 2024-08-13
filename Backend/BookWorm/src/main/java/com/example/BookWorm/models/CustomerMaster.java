package com.example.BookWorm.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_master")
public class CustomerMaster {
public CustomerMaster() {}
    public MyShelf getShelf() {
	return shelf;
}

public void setShelf(MyShelf shelf) {
	this.shelf = shelf;
}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_email", length = 50, unique = true, nullable = false)
    private String customerEmail;

    @Column(name = "customer_password", length = 40, nullable = false)
    private String customerPassword;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(name = "pan")
    private String pan;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartMaster cart;
    @Column(name = "Library_Package_Expiry_date", nullable = true)
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "library_package_id")
    private LibraryPackage libraryPackage;

    public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	// Getters and Setters
    @ManyToOne
    @JoinColumn(name="shelf_id",nullable=true)
      private MyShelf shelf;
    public CustomerMaster(Long customerId2) {
		// TODO Auto-generated constructor stub
	this.customerId=customerId2;
    }

	public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public CartMaster getCart() {
        return cart;
    }

    public void setCart(CartMaster cart) {
        this.cart = cart;
    }

    public LibraryPackage getLibraryPackage() {
        return libraryPackage;
    }

    public void setLibraryPackage(LibraryPackage libraryPackage) {
        this.libraryPackage = libraryPackage;
    }
}
