package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
@Repository("JobApplicationDao")
@Transactional
public class JobApplicationDaoImpl implements JobApplicationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public JobApplicationDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession(){
		return sessionFactory.openSession();
	}

	public boolean saveJobApplication(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(jobApplication);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	
	}
	
	public boolean updateJobApplication(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(jobApplication);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	
	}
	
	public List<JobApplication> jobApplicationsByJobId(int jobId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from JobApplication where jobId = ?");
			query.setInteger(0, jobId);
			
			return query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<JobApplication> jobApplicationsByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from JobApplication where userId = ?");
			query.setString(0, userId);
			
			return query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean isJobExist(String userId, int jobId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from JobApplication where userId = ? and jobId = ?");
			query.setString(0, userId);
			query.setInteger(1, jobId);
			
			return !query.list().isEmpty();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		
	}

	public JobApplication getJobApplicationByJobApplicationId(int jobApplicationId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from JobApplication where jobApplicationId = ?");
			query.setInteger(0, jobApplicationId);
			
			
			return (JobApplication)query.uniqueResult();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
	
	}

}


