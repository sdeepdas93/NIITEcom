package com.niit.ecomweb1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ProductSubCategory implements Serializable{
	@Id
	@GeneratedValue
	private int productSubCategoryId;
	private boolean productSubCategoryStatus;
	
	private String productSubCategoryName;
	@OneToMany(mappedBy="productSubCategory")
	private List<Product> products;
	@ManyToOne
	@JoinColumn(name="productCategoryId")
	private ProductCategory productCategory;
	private String productSubCategoryDetails;
	@Transient
	private MultipartFile productSubCategoryImageFile;
	private String productSubcategoryImage;
	public int getProductSubCategoryId() {
		return productSubCategoryId;
	}
	public void setProductSubCategoryId(int productSubCategoryId) {
		this.productSubCategoryId = productSubCategoryId;
	}
	public String getProductSubCategoryName() {
		return productSubCategoryName;
	}
	public void setProductSubCategoryName(String productSubCategoryName) {
		this.productSubCategoryName = productSubCategoryName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	public String getProductSubCategoryDetails() {
		return productSubCategoryDetails;
	}
	public void setProductSubCategoryDetails(String productSubCategoryDetails) {
		this.productSubCategoryDetails = productSubCategoryDetails;
	}
	public MultipartFile getProductSubCategoryImageFile() {
		return productSubCategoryImageFile;
	}
	public void setProductSubCategoryImageFile(MultipartFile productSubCategoryImageFile) {
		this.productSubCategoryImageFile = productSubCategoryImageFile;
	}
	public String getProductSubcategoryImage() {
		return productSubcategoryImage;
	}
	public void setProductSubcategoryImage(String productSubcategoryImage) {
		this.productSubcategoryImage = productSubcategoryImage;
	}
	public boolean isProductSubCategoryStatus() {
		return productSubCategoryStatus;
	}
	public void setProductSubCategoryStatus(boolean productSubCategoryStatus) {
		this.productSubCategoryStatus = productSubCategoryStatus;
	}
	

}
