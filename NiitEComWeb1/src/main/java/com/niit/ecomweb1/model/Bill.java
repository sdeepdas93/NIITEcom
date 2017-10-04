package com.niit.ecomweb1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class Bill {
	@Id
	private int BillId;
	
	@OneToOne
	@JoinColumn(name="addressId")
	private Address address;
	@OneToOne
	@JoinColumn(name="orderId")
	private Order order;
	@OneToOne
	@JoinColumn(name="billDetailsId")
	BillDetails billDetails;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	public int getBillId() {
		return BillId;
	}
	public void setBillId(int billId) {
		BillId = billId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public BillDetails getBillDetails() {
		return billDetails;
	}
	public void setBillDetails(BillDetails billDetails) {
		this.billDetails = billDetails;
	}
	

}
