package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.ProductCategory;




public interface ProductCategoryDao {
	public boolean insertProductCategory(ProductCategory productCategory);
	public boolean deleteProductCategory(ProductCategory productCategory);
	public boolean updateProductCategory(ProductCategory productCategory);
	public List<ProductCategory> getAllProductCategorys();
	public ProductCategory getProductCategoryByProductCategoryId(int productCategoryId);
}
