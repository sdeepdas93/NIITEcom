package com.niit.ecomweb1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails implements Serializable{
	
	@Id 
	@GeneratedValue
	private int orderDetailsId;
	
	private String OrderDetailsStatus;
	
	@OneToMany(mappedBy="orderDetails")
	List<CartItem> cartItems;
	
	private Date orderDetailsDate;

	public Date getOrderDetailsDate() {
		return orderDetailsDate;
	}
	public void setOrderDetailsDate(Date orderDetailsDate) {
		this.orderDetailsDate = orderDetailsDate;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	User user;
	
	private String orderDetailsAddress;
	private int orderDetailsPin;
	private double orderDetailsTotal;
	/*@OneToOne
	@JoinColumn(name="addressId")
	Address address;*/
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public int getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	public String getOrderDetailsAddress() {
		return orderDetailsAddress;
	}
	public void setOrderDetailsAddress(String orderDetailsAddress) {
		this.orderDetailsAddress = orderDetailsAddress;
	}
	public int getOrderDetailsPin() {
		return orderDetailsPin;
	}
	public void setOrderDetailsPin(int orderDetailsPin) {
		this.orderDetailsPin = orderDetailsPin;
	}
	public double getOrderDetailsTotal() {
		return orderDetailsTotal;
	}
	public void setOrderDetailsTotal(double orderDetailsTotal) {
		this.orderDetailsTotal = orderDetailsTotal;
	}
	public String getOrderDetailsStatus() {
		return OrderDetailsStatus;
	}
	public void setOrderDetailsStatus(String orderDetailsStatus) {
		OrderDetailsStatus = orderDetailsStatus;
	}
	

	
	
	/*public Address getAaddress() {
		return address;
	}
	public void setAaddress(Address address) {
		this.address = address;
	}
	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}*/
	

}
