package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.ProductCategory;
import com.niit.ecomweb1.model.ProductSubCategory;

public interface ProductSubCategoryDao {
	public boolean insertProductSubCategory(ProductSubCategory productSubCategory);
	public boolean deleteProductSubCategory(ProductSubCategory productSubCategory);
	public boolean updateProductSubCategory(ProductSubCategory productSubCategory);
	public List<ProductSubCategory> getAllProductSubCategorys();
	public List<ProductSubCategory> getProductSubCategoriesByProductCategory(ProductCategory productCategory);
	public ProductSubCategory getProductSubcategoryByProductSubCategoryId(int productSubCategoryId);

}
