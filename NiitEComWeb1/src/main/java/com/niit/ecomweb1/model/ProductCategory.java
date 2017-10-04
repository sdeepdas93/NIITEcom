package com.niit.ecomweb1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ProductCategory {
	@Id 
	private int productCategoryId;
	private String productCategoryName;
	private String productCategoryDetails;
	@OneToMany(mappedBy="productCategory")
	List<ProductSubCategory> productSubCategories;
	@Transient
	private MultipartFile productCategoryImageFile;
	private String productCategoryImage;
	public int getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public String getProductCategoryDetails() {
		return productCategoryDetails;
	}
	public void setProductCategoryDetails(String productCategoryDetails) {
		this.productCategoryDetails = productCategoryDetails;
	}
	public List<ProductSubCategory> getProductSubCategories() {
		return productSubCategories;
	}
	public void setProductSubCategories(List<ProductSubCategory> productSubCategories) {
		this.productSubCategories = productSubCategories;
	}
	public MultipartFile getProductCategoryImageFile() {
		return productCategoryImageFile;
	}
	public void setProductCategoryImageFile(MultipartFile productCategoryImageFile) {
		this.productCategoryImageFile = productCategoryImageFile;
	}
	public String getProductCategoryImage() {
		return productCategoryImage;
	}
	public void setProductCategoryImage(String productCategoryImage) {
		this.productCategoryImage = productCategoryImage;
	}
	
	
	

}
