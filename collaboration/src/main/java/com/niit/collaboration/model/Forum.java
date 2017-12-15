package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Entity
@Component
public class Forum extends BaseDomain implements Serializable {


	
	@Id
	@GeneratedValue
	private int forumId;
	private String forumName;
	private String forumDescription;
	private String forumCreaterId;
	private Date forumCreationDate;
	private String forumStatus;
	private boolean forumEnable;
	private String forumReason;
	private int forumCountLike;
	private int forumCountComment;
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public String getForumDescription() {
		return forumDescription;
	}
	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}
	public String getForumCreaterId() {
		return forumCreaterId;
	}
	public void setForumCreaterId(String forumCreaterId) {
		this.forumCreaterId = forumCreaterId;
	}
	public Date getForumCreationDate() {
		return forumCreationDate;
	}
	public void setForumCreationDate(Date forumCreationDate) {
		this.forumCreationDate = forumCreationDate;
	}
	public String getForumStatus() {
		return forumStatus;
	}
	public void setForumStatus(String forumStatus) {
		this.forumStatus = forumStatus;
	}
	public boolean isForumEnable() {
		return forumEnable;
	}
	public void setForumEnable(boolean forumEnable) {
		this.forumEnable = forumEnable;
	}
	public String getForumReason() {
		return forumReason;
	}
	public void setForumReason(String forumReason) {
		this.forumReason = forumReason;
	}
	public int getForumCountLike() {
		return forumCountLike;
	}
	public void setForumCountLike(int forumCountLike) {
		this.forumCountLike = forumCountLike;
	}
	public int getForumCountComment() {
		return forumCountComment;
	}
	public void setForumCountComment(int forumCountComment) {
		this.forumCountComment = forumCountComment;
	}
	
	
	
	
	
	

}
