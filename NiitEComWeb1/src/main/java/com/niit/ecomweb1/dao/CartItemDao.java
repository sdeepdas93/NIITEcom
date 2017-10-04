package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.CartItem;



public interface CartItemDao {
	public boolean insertCartItem(CartItem cartItem);
	public boolean deletecartItem(CartItem cartItem);
	public boolean updatecartItem(CartItem cartItem);
	public List<CartItem> getAllCartItems();
	public List<CartItem> getCartItemsByCart(Cart cart);
	public CartItem getCartItembyCartItemId(int cartItemId);

}
