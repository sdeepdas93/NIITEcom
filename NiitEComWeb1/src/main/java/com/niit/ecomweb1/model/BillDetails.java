package com.niit.ecomweb1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BillDetails {
	@Id
	private int billDetailsId;
	@OneToOne(mappedBy="billDetails")
	private Bill bill;
	public int getBillDetailsId() {
		return billDetailsId;
	}
	public void setBillDetailsId(int billDetailsId) {
		this.billDetailsId = billDetailsId;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	

}
