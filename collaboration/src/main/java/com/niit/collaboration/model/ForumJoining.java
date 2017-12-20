package com.niit.collaboration.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Entity
@Component

public class ForumJoining extends BaseDomain implements Serializable {
	@Id
	@GeneratedValue
	private int forumJoiningId;
	private String userId;
	private int forumId;
	private String userName;
	public int getForumJoiningId() {
		return forumJoiningId;
	}
	public void setForumJoiningId(int forumJoiningId) {
		this.forumJoiningId = forumJoiningId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
