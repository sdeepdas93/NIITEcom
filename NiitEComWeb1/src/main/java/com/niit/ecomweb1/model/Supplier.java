package com.niit.ecomweb1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Supplier {
	@Id
	private int supplierId;
	private String supplierName;
	private String supplierContactNo1;
	private String supplierContactNo2;
	@OneToOne
	@JoinColumn(name="addressId")
	private Address address;
	@OneToMany(mappedBy="supplier")
	List<Product> products;
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierContactNo1() {
		return supplierContactNo1;
	}
	public void setSupplierContactNo1(String supplierContactNo1) {
		this.supplierContactNo1 = supplierContactNo1;
	}
	public String getSupplierContactNo2() {
		return supplierContactNo2;
	}
	public void setSupplierContactNo2(String supplierContactNo2) {
		this.supplierContactNo2 = supplierContactNo2;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	

}
