package com.niit.ecomweb1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductReview {
	@Id
	private int ProductReviewId;
	@ManyToOne
	@JoinColumn(name="userId")
	User user;
	@ManyToOne
	@JoinColumn(name="productId")
	Product product;
	private String productReviewText;
	public int getProductReviewId() {
		return ProductReviewId;
	}
	public void setProductReviewId(int productReviewId) {
		ProductReviewId = productReviewId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProductReviewText() {
		return productReviewText;
	}
	public void setProductReviewText(String productReviewText) {
		this.productReviewText = productReviewText;
	}
	

	

}
