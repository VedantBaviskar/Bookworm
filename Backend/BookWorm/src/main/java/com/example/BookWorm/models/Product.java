package com.example.BookWorm.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "product_master")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_image", length = 200, nullable = true)
    private String productImage;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_english_name")
    private String productEnglishName;

    @OneToOne
    @JoinColumn(name = "TypeId",referencedColumnName = "TypeId")
    private ProductType productType;


    @Column(name = "product_baseprice")
    private Double productBaseprice;

    @Column(name = "product_sp_cost")
    private Double productSpCost;

    @Column(name = "product_offerprice")
    private Double productOfferprice;

    @Column(name = "product_off_price_expirydate")
    @Temporal(TemporalType.DATE)
    private Date productOffPriceExpirydate;

    @Column(name = "product_description_short")
    private String productDescriptionShort;

    @Column(name = "product_description_long", length = 500)
    private String productDescriptionLong;

    @Column(name = "product_isbn")
    private String productIsbn;

    @Column(name = "product_author")
    private String productAuthor;

    @ManyToOne
    @JoinColumn(name = "product_publisher_id", referencedColumnName = "Publisher_Id")
    private PublisherMaster productPublisher;

    @OneToOne
    @JoinColumn(name = "product_lang_id", referencedColumnName = "Type-Id")
    private Language productLang;

    @Column(name = "is_rentable", nullable = false)
    private Boolean isRentable;

    @Column(name = "is_library", nullable = false)
    private Boolean isLibrary;

    @Column(name = "rent_per_day")
    private Double rentPerDay;

    @Column(name = "min_rent_days")
    private Integer minRentDays;

//    @OneToMany(mappedBy = "product")
//    @JsonBackReference
//    private Set<ProductGenre> productGenres;
//
//    @OneToMany(mappedBy = "product1")
//    private Set<Product_Type_Attribute_Value> ptal;
//
//    @OneToMany(mappedBy = "product")
//    @JsonBackReference
//    private Set<CartDetails> cartDetails;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductEnglishName() {
        return productEnglishName;
    }

    public void setProductEnglishName(String productEnglishName) {
        this.productEnglishName = productEnglishName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getProductBaseprice() {
        return productBaseprice;
    }

    public void setProductBaseprice(Double productBaseprice) {
        this.productBaseprice = productBaseprice;
    }

    public Double getProductSpCost() {
        return productSpCost;
    }

    public void setProductSpCost(Double productSpCost) {
        this.productSpCost = productSpCost;
    }

    public Double getProductOfferprice() {
        return productOfferprice;
    }

    public void setProductOfferprice(Double productOfferprice) {
        this.productOfferprice = productOfferprice;
    }

    public Date getProductOffPriceExpirydate() {
        return productOffPriceExpirydate;
    }

    public void setProductOffPriceExpirydate(Date productOffPriceExpirydate) {
        this.productOffPriceExpirydate = productOffPriceExpirydate;
    }

    public String getProductDescriptionShort() {
        return productDescriptionShort;
    }

    public void setProductDescriptionShort(String productDescriptionShort) {
        this.productDescriptionShort = productDescriptionShort;
    }

    public String getProductDescriptionLong() {
        return productDescriptionLong;
    }

    public void setProductDescriptionLong(String productDescriptionLong) {
        this.productDescriptionLong = productDescriptionLong;
    }

    public String getProductIsbn() {
        return productIsbn;
    }

    public void setProductIsbn(String productIsbn) {
        this.productIsbn = productIsbn;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }

    public PublisherMaster getProductPublisher() {
        return productPublisher;
    }

    public void setProductPublisher(PublisherMaster productPublisher) {
        this.productPublisher = productPublisher;
    }

    public Language getProductLang() {
        return productLang;
    }

    public void setProductLang(Language productLang) {
        this.productLang = productLang;
    }

    public Boolean getIsRentable() {
        return isRentable;
    }

    public void setIsRentable(Boolean isRentable) {
        this.isRentable = isRentable;
    }

    public Boolean getIsLibrary() {
        return isLibrary;
    }

    public void setIsLibrary(Boolean isLibrary) {
        this.isLibrary = isLibrary;
    }

    public Double getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(Double rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public Integer getMinRentDays() {
        return minRentDays;
    }

    public void setMinRentDays(Integer minRentDays) {
        this.minRentDays = minRentDays;
    }

//    public Set<ProductGenre> getProductGenres() {
//        return productGenres;
//    }
//
//    public void setProductGenres(Set<ProductGenre> productGenres) {
//        this.productGenres = productGenres;
//    }
//
//    public Set<Product_Type_Attribute_Value> getPtaValues() {
//        return ptal;
//    }
//
//    public void setPtaValues(Set<Product_Type_Attribute_Value> ptal) {
//        this.ptal = ptal;
//    }
//
//    public Set<CartDetails> getCartDetails() {
//        return cartDetails;
//    }
//
//    public void setCartDetails(Set<CartDetails> cartDetails) {
//        this.cartDetails = cartDetails;
//    }
}
