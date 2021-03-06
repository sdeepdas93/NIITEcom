package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.CartItem;
import com.niit.ecomweb1.model.OrderDetails;



public interface CartItemDao {
	public boolean insertCartItem(CartItem cartItem);
	public boolean deleteCartItem(CartItem cartItem);
	public boolean updateCartItem(CartItem cartItem);
	public List<CartItem> getAllCartItems();
	public List<CartItem> getCartItemsByCart(Cart cart);
	public CartItem getCartItembyCartItemId(int cartItemId);
	public List<CartItem> getCartItemsByOrderdetails(OrderDetails orderDetails);

}
