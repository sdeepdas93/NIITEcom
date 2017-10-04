package com.niit.ecomweb1.dao;

import java.util.List;



import com.niit.ecomweb1.model.CartItem;
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductBrand;
import com.niit.ecomweb1.model.ProductSubCategory;

public interface ProductDao {
	public boolean insertProduct(Product product);
	public boolean deleteProduct(Product product);
	public boolean updateProduct(Product product);
	public List<Product> getAllProducts();
	public List<Product> getProdctsByProductBrand(ProductBrand productBrand);
	public List<Product> getProductsByProductSubCategory(ProductSubCategory productSubCategory);
	public Product getProductByCartitem(CartItem cartItem);
	public Product getProductByProductId(int productId);
	

}
