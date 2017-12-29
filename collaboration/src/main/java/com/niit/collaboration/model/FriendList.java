package com.niit.collaboration.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;


@Entity
@Component
public class FriendList extends BaseDomain implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_FRIENDLIST_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	
	private int friendListId;
	private String userId;
	private String friendId;
	private String friendListStatus;
	
	public int getFriendListId() {
		return friendListId;
	}
	public void setFriendListId(int friendListId) {
		this.friendListId = friendListId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getFriendListStatus() {
		return friendListStatus;
	}
	public void setFriendListStatus(String friendListStatus) {
		this.friendListStatus = friendListStatus;
	} 
	

}
