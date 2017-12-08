package com.niit.collaboration.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Entity
@Component
public class JobApplication extends BaseDomain implements Serializable {
	@Id
	@GeneratedValue
	private int jobApplicationId;
	private String userId;
	private int jobId;
	private String jobApplicationStatus;
	public int getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(int jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobApplicationStatus() {
		return jobApplicationStatus;
	}
	public void setJobApplicationStatus(String jobApplicationStatus) {
		this.jobApplicationStatus = jobApplicationStatus;
	}
	

}
