package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Entity
@Component
public class Job extends BaseDomain implements Serializable {
	@Id
	@GeneratedValue
	private int jobId;
	private String jobProfile;
	private String jobDescriptioin;
	private String jobQualification;
	private String jobStatus;
	private Date jobPostDate;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobProfile() {
		return jobProfile;
	}
	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}
	public String getJobDescriptioin() {
		return jobDescriptioin;
	}
	public void setJobDescriptioin(String jobDescriptioin) {
		this.jobDescriptioin = jobDescriptioin;
	}
	public String getJobQualification() {
		return jobQualification;
	}
	public void setJobQualification(String jobQualification) {
		this.jobQualification = jobQualification;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Date getJobPostDate() {
		return jobPostDate;
	}
	public void setJobPostDate(Date jobPostDate) {
		this.jobPostDate = jobPostDate;
	}
	

}
