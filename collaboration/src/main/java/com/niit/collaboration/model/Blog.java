package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Blog extends BaseDomain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	String blogId;
	String blogReason;
	String blogContent;
	String blogTitle;
	Date blogDate;
	String blogUserId;
	String blogStatus;
	int blogCountLike;
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getBlogReason() {
		return blogReason;
	}
	public void setBlogReason(String blogReason) {
		this.blogReason = blogReason;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public Date getBlogDate() {
		return blogDate;
	}
	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}
	public String getBlogUserId() {
		return blogUserId;
	}
	public void setBlogUserId(String blogUserId) {
		this.blogUserId = blogUserId;
	}
	public String getBlogStatus() {
		return blogStatus;
	}
	public void setBlogStatus(String blogStatus) {
		this.blogStatus = blogStatus;
	}
	public int getBlogCountLike() {
		return blogCountLike;
	}
	public void setBlogCountLike(int blogCountLike) {
		this.blogCountLike = blogCountLike;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	
	

}