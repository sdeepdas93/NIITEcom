package com.niit.ecomweb1.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class CartItem implements Serializable{
	@Id
	@GeneratedValue
	private int cartItemId;
	
	@OneToOne
	@JoinColumn(name="productId")
	private Product product;
	@ManyToOne
	@JoinColumn(name="cartId")
	private Cart cart;
	private int cartItemQuantity;
	private double cartItemSubtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public int getCartItemQuantity() {
		return cartItemQuantity;
	}
	public void setCartItemQuantity(int cartItemQuantity) {
		this.cartItemQuantity = cartItemQuantity;
	}
	
	
	
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	public double getCartItemSubtotal() {
		return cartItemSubtotal;
	}
	public void setCartItemSubtotal(double cartItemSubtotal) {
		this.cartItemSubtotal = cartItemSubtotal;
	}
	
}
