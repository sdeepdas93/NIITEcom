package com.niit.collaboration.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OutputMessage extends Message {
    
	private Date time;

	public OutputMessage(Message original, Date time) {
		super(original.getMessage(), original.getId(),original.getUserId(),original.getUserName(),original.getUserGender());
		this.time = time;
	}

	/**
	 *  
	 *  getters/setters for all the fields taken... 
	 *  
	 */
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
