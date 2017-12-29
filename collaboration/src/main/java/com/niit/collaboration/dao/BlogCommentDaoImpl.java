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
import com.niit.collaboration.model.User;

@Repository("blogCommentDao")
@Transactional

public class BlogCommentDaoImpl implements BlogCommentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public BlogCommentDaoImpl(SessionFactory sessionFactory){
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

	

	public List<BlogComment> getAllBlogComments() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from BlogComment");
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(blogComment);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(blogComment);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(blogComment);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public BlogComment getBlogCommentByBlogCommentId(int blogCommentId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from BlogComment where blogCommentId = ?");
			query.setParameter(0, blogCommentId);
			return (BlogComment) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<BlogComment> getBlogCommentsByBlogId(int blogId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from BlogComment where blogId = ?");
			query.setParameter(0, blogId);
			return  query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
