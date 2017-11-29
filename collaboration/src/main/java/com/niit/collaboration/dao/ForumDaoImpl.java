package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Forum;


@Repository("ForumDao")
@Transactional
public class ForumDaoImpl implements ForumDao {

	@Autowired
	private SessionFactory sessionFactory;
	

	public ForumDaoImpl(SessionFactory sessionFactory){
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
	
	public List<Forum> getAllForums() {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from Forum");
			List<Forum> forums=query.list();
			return forums;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(forum);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean deleteForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(forum);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(forum);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public Forum getForumByForumId(int forumId) {
		// TODO Auto-generated method stub
Session session=getSession();
		
		try{
			Query query=session.createQuery("from Forum where forumId = ?");
			query.setInteger(0, forumId);
			Forum forum=(Forum) query.uniqueResult();
			return forum;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
	

	
	
	
		


}
