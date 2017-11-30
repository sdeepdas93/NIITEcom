package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Job;

@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao{

	@Autowired
	private SessionFactory sessionFactory;
	

	public JobDaoImpl(SessionFactory sessionFactory){
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
	
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from Job");
			List<Job> jobs=query.list();
			return jobs;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveJob(Job job) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(job);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public Job getJobByJobId(int jobId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from Job where jobId = ?");
			query.setInteger(0, jobId);
			Job job=(Job) query.uniqueResult();
			return job;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}



