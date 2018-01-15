package com.niit.collaboration.model;


public class Message {

	private String message;
	private int id;
	private String name;
	private String userName;
	private String userGender;
	//**edited
	private String userId;
	
	public Message() {
		
	}
	public Message(String message, int id,String userId,String userName,String userGender) {
		this.message = message;
		this.id = id;
		this.userId=userId;
		this.userName=userName;
		this.userGender=userGender;
	}
	
	/**
	 *  
	 *  getters/setters for all the fields taken... 
	 *  
	 */
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	
}
