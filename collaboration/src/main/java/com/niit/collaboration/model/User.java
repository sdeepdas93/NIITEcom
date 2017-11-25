package com.niit.collaboration.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Component
public class User extends BaseDomain implements Serializable {
	@Id
	@NotEmpty(message="Required")
	private String userId;
	@NotEmpty(message="Required")
	private String userPassword;
	@NotEmpty(message="Required")
	private String userName;
	@NotEmpty(message="Required")
	private String userRole;
	@NotEmpty(message="Required")
	private String userGender;
	
	private Date userDob;
	@NotEmpty(message="Required")
	private String userEmail;
	@NotEmpty(message="Required")
	private String userContactNumber;
	@NotEmpty(message="Required")
	private String userAddress;
	@NotEmpty(message="Required")
	private String userStatus;
	@NotEmpty(message="Required")
	private String userIsOnline;
	@NotEmpty(message="Required")
	private String userImage;
	@NotEmpty(message="Required")
	private String userDescription;
	
	@Transient
	
	private MultipartFile userImageFile;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Date getUserDob() {
		return userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserContactNumber() {
		return userContactNumber;
	}

	public void setUserContactNumber(String userContactNumber) {
		this.userContactNumber = userContactNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserIsOnline() {
		return userIsOnline;
	}

	public void setUserIsOnline(String userIsOnline) {
		this.userIsOnline = userIsOnline;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public MultipartFile getUserImageFile() {
		return userImageFile;
	}

	public void setUserImageFile(MultipartFile userImageFile) {
		this.userImageFile = userImageFile;
	}
	
	
	
	
	
	

}
