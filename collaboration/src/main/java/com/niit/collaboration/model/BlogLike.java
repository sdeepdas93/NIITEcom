package com.niit.collaboration.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

/**
 * @author Subhadeep Das
 *
 */
@Entity
@Component
public class BlogLike  extends BaseDomain implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_BLOGLIKE_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int blogLikeId;
	private String userId;
	private int blogId;
	private String  userName;
	public int getBlogLikeId() {
		return blogLikeId;
	}
	public void setBlogLikeId(int blogLikeId) {
		this.blogLikeId = blogLikeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	


}
