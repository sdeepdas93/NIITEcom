package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.JobApplication;

public interface JobApplicationDao {
	public boolean saveJobApplication(JobApplication jobApplication);
	public boolean updateJobApplication(JobApplication jobApplication);
	public JobApplication getJobApplicationByJobApplicationId(int jobApplicationId);
	public List<JobApplication> jobApplicationsByJobId(int jobId);
	public List<JobApplication> jobApplicationsByUserId(String userId);
	public boolean isJobExist(String userId, int jobId); 
}
