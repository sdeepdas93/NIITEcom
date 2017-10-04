package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.User;

public interface CartDao {
	public boolean insertCart(Cart cart);
	public boolean deleteCart(Cart cart);
	public boolean updateCart(Cart cart);
	public List<Cart> getAllCart();
	public Cart getCartByUser(User user);
	public Cart getCartByCartId(int cartId);
	

}
