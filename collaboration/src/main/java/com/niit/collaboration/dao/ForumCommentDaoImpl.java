package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.BlogComment;
import com.niit.collaboration.model.ForumComment;


@Repository("forumCommentDao")
@Transactional

public class ForumCommentDaoImpl implements ForumCommentDao {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public ForumCommentDaoImpl(SessionFactory sessionFactory){
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

	

	public List<ForumComment> getAllForumComments() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ForumComment");
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveForumComment(ForumComment forumComment) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(forumComment);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteForumComment(ForumComment forumComment) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(forumComment);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForumComment(ForumComment forumComment) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(forumComment);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public ForumComment getForumCommentByForumCommentId(int forumCommentId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ForumComment where forumCommentId = ?");
			query.setParameter(0, forumCommentId);
			return (ForumComment) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ForumComment> getForumCommentsByForumId(int forumId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ForumComment where forumId = ?");
			query.setParameter(0, forumId);
			return  query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
