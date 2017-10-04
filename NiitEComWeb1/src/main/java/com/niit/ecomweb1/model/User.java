package com.niit.ecomweb1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class User {
	@Id
	private String userId;
	private String password;
	private String userRole;
	private String userName;
	private String userEmailId;
	private String sex;
	private String userContactNo1;
	private String userContactNo2;
	@Transient
	private MultipartFile userImageFile;
	
	private String userImage;
	@OneToMany(mappedBy="user")
	private List<ProductReview>  productReviews;
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	@OneToMany(mappedBy="user")
	private List<Bill> bills;
	
	@OneToOne
	@JoinColumn(name="addressId")
	Address address;
	
	
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserContactNo1() {
		return userContactNo1;
	}
	public void setUserContactNo1(String userContactNo1) {
		this.userContactNo1 = userContactNo1;
	}
	public String getUserContactNo2() {
		return userContactNo2;
	}
	public void setUserContactNo2(String userContactNo2) {
		this.userContactNo2 = userContactNo2;
	}
	public MultipartFile getUserImageFile() {
		return userImageFile;
	}
	public void setUserImageFile(MultipartFile userImageFile) {
		this.userImageFile = userImageFile;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public List<ProductReview> getProductReviews() {
		return productReviews;
	}
	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	
	
	

}
