package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Job;

public interface JobDao {
	public boolean saveJob(Job job);
	public Job getJobByJobId(int jobId);
	
	public List<Job> getAllJobs();
	

}
