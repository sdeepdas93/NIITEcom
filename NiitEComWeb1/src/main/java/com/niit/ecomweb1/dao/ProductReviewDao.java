package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductReview;

public interface ProductReviewDao {
	public boolean insertProductReview(ProductReview productReview);
	public boolean deleteProductReview(ProductReview productReview);
	public boolean updateProductReview(ProductReview productReview);
	public List<ProductReview> getAllProductReviews();
	public List<ProductReview> getProductReviewsByProduct(Product product);
	public ProductReview getProductReviewByProductReviewId(int productReviewId);
	
	
}
