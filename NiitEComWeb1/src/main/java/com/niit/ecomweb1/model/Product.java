package com.niit.ecomweb1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Product implements Serializable {
	@Id
	private int productId;
	private String productName;
	private double productPrice;
	private String productInfo;
	private double productRaiting;
	@ManyToOne
	@JoinColumn(name="productSubCategoryId")
	ProductSubCategory productSubCategory;
	@ManyToOne
	@JoinColumn(name="productBrandId")
	ProductBrand productBrand;
	
	//need some thing for cartitem X product
	
	@OneToMany(mappedBy="product")
	List<ProductReview> productReviews;
	
	@ManyToOne
	@JoinColumn(name="supplierId")
	private Supplier supplier;
	
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@Transient
	private MultipartFile userImageFile;
	private String userImage;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public double getProductRaiting() {
		return productRaiting;
	}
	public void setProductRaiting(double productRaiting) {
		this.productRaiting = productRaiting;
	}
	public ProductSubCategory getProductSubCategory() {
		return productSubCategory;
	}
	public void setProductSubCategory(ProductSubCategory productSubCategory) {
		this.productSubCategory = productSubCategory;
	}
	public ProductBrand getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(ProductBrand productBrand) {
		this.productBrand = productBrand;
	}
	public List<ProductReview> getProductReviews() {
		return productReviews;
	}
	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}
	public MultipartFile getUserImageFile() {
		return userImageFile;
	}
	public void setUserImageFile(MultipartFile userImageFile) {
		this.userImageFile = userImageFile;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	

}
