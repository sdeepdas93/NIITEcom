package com.niit.collaboration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.JobApplicationDao;
import com.niit.collaboration.dao.JobDao;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.ForumComment;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.User;

@RestController
public class JobController {
	@Autowired
	JobDao jobDao;
	@Autowired
	JobApplicationDao jobApplicationDao;
	
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs(HttpSession session) {
		try{
		User user=(User) session.getAttribute("loggedInUser");
		if(user==null)  return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		List<Job> jobs = jobDao.getAllJobs();
		if (jobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		

		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		}catch(NullPointerException e){
			System.out.println("user not logged in");
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
			
			
	}
	
	@GetMapping(value = "/jobs/{jobId}")
	public ResponseEntity<Job> getJobByJobId(@PathVariable("jobId") int jobId,HttpSession session){
		try{
				User user=(User) session.getAttribute("loggedInUser");
				System.out.println(user.getUserName()+" at getJobByJobId");
				Job job=jobDao.getJobByJobId(jobId);
				if(job==null){
					job=new Job();
					job.setErrorCode("404");
					job.setErrorMessage("job not found");
					return new ResponseEntity<Job>(job,HttpStatus.OK);
				}else{
					return new ResponseEntity<Job>(job,HttpStatus.OK);
					}
			
			
		}catch(NullPointerException e){
			Job job=new Job();
			job.setErrorCode("404");
			job.setErrorMessage("user not logged in");
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	}
	
	
	@PostMapping(value="/job/")
	public ResponseEntity<Job> saveJob(@RequestBody Job job,HttpSession session){
		try{
		User user=(User) session.getAttribute("loggedInUser");
		
		if(!(user.getUserRole().equals("ADMIN_USER"))){
			
			job.setErrorCode("404");
			job.setErrorMessage("Forum Not Created");
			return new ResponseEntity<Job>(job,HttpStatus.OK);
				}
		}catch(NullPointerException e){
			job.setErrorCode("404");
			job.setErrorMessage("Admin Not logged in");
			return new ResponseEntity<Job>(job,HttpStatus.OK);
			
		}
		job.setJobPostDate(new Date(System.currentTimeMillis()));
		job.setJobStatus("A");
		jobDao.saveJob(job);
		
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	

	//for job application
	
	
	@PostMapping(value="/jobApplication/{jobId}")
	public ResponseEntity<JobApplication> saveJobApplication(@PathVariable("jobId")int jobId, HttpSession session){
		try{
		User user=(User) session.getAttribute("loggedInUser");
		String userId=user.getUserId();
			if(jobApplicationDao.isJobExist(userId, jobId)){
				JobApplication jobApplication=new JobApplication();
				jobApplication.setErrorCode("404");
				jobApplication.setErrorMessage("JobApplication Not Created");
				return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
			}
			
			JobApplication jobApplication=new JobApplication();
			jobApplication.setJobId(jobId);
			jobApplication.setUserId(userId);
			jobApplication.setJobApplicationStatus("N");
			jobApplicationDao.saveJobApplication(jobApplication);
			return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
		
		
		}catch(NullPointerException e){
			e.printStackTrace();
			JobApplication jobApplication=new JobApplication();
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("User Not loggedin");
			return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
		}
		
		
	}
	
	@PutMapping(value="/approveJobApplication/{jobApplicationId}")
	public ResponseEntity<JobApplication>approveJobApplication(@PathVariable("jobApplicationId") int jobApplicationId,HttpSession session){
		try{
			User user=(User) session.getAttribute("loggedInUser");
			JobApplication jobApplication=jobApplicationDao.getJobApplicationByJobApplicationId(jobApplicationId);
			if((user.getUserRole().equals("ADMIN_USER"))&&(jobApplication!=null)){
				jobApplication.setJobApplicationStatus("A");
				jobApplicationDao.updateJobApplication(jobApplication);
				return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
			}else{
				 jobApplication=new JobApplication();
				jobApplication.setErrorCode("404");
				jobApplication.setErrorMessage("not found");
				return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			JobApplication jobApplication=new JobApplication();
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("admin not logged in");
			return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
		}
	}
	
	@PutMapping(value="/rejectJobApplication/{jobApplicationId}")
	public ResponseEntity<JobApplication>rejectJobApplication(@PathVariable("jobApplicationId") int jobApplicationId,HttpSession session){
		try{
			User user=(User) session.getAttribute("loggedInUser");
			JobApplication jobApplication=jobApplicationDao.getJobApplicationByJobApplicationId(jobApplicationId);
			if((user.getUserRole().equals("ADMIN_USER"))&&(jobApplication!=null)){
				jobApplication.setJobApplicationStatus("R");
				jobApplicationDao.updateJobApplication(jobApplication);
				return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
			}else{
				 jobApplication=new JobApplication();
				jobApplication.setErrorCode("404");
				jobApplication.setErrorMessage("not found");
				return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			JobApplication jobApplication=new JobApplication();
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("admin not logged in");
			return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/appliedJobs")
	public ResponseEntity<List<JobApplication>> getAppliedJobs(HttpSession session){
		
		try{
		
			User user=(User) session.getAttribute("loggedInUser");
			
			
			return new ResponseEntity<List<JobApplication>>(jobApplicationDao.jobApplicationsByUserId(user.getUserId()),HttpStatus.OK);
		}catch(NullPointerException e){ 
			e.printStackTrace(); 
			System.out.println("user not logged in");
			return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@GetMapping(value="/allApplications/{jobId}")
	public ResponseEntity<List<JobApplication>> getJobApplications(@PathVariable("jobId")int jobId, HttpSession session){
		try{
			User user=(User) session.getAttribute("loggedInUser");
			if((!user.getUserRole().equals("ADMIN_USER"))||
				(jobDao.getJobByJobId(jobId)==null)){
				return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<JobApplication>>(jobApplicationDao.jobApplicationsByJobId(jobId),HttpStatus.OK);
		}catch(NullPointerException e){
			e.printStackTrace();
			System.out.println("user not logged in");
			return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
		}
		
	}
	

}
