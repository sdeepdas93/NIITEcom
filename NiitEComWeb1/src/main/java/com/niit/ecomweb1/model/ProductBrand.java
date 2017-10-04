package com.niit.ecomweb1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ProductBrand {
	@Id
	private int productBrandId;
	private String productBrandName;
	@OneToMany(mappedBy="productBrand")
	List<Product> products;
	
	@Transient
	private MultipartFile productBrandImageFile;
	private String productBrandImage;
	public MultipartFile getProductBrandImageFile() {
		return productBrandImageFile;
	}
	public void setProductBrandImageFile(MultipartFile productBrandImageFile) {
		this.productBrandImageFile = productBrandImageFile;
	}
	public String getProductBrandImage() {
		return productBrandImage;
	}
	public void setProductBrandImage(String productBrandImage) {
		this.productBrandImage = productBrandImage;
	}
	public int getProductBrandId() {
		return productBrandId;
	}
	public void setProductBrandId(int productBrandId) {
		this.productBrandId = productBrandId;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
