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


@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	
	
@Autowired
	private SessionFactory sessionFactory;
	

	public BlogDaoImpl(SessionFactory sessionFactory){
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
		

	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		Session session=getSession();
		
		try{
			Query query=session.createQuery("from Blog");
			List<Blog> blogs=query.list();
			return blogs;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	public Blog getBlogByBlogId(int blogId) {
		// TODO Auto-generated method stub
Session session=getSession();
		
		try{
			Query query=session.createQuery("from Blog where blogId = ?");
			query.setInteger(0, blogId);
			Blog blog=(Blog) query.uniqueResult();
			return blog;
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
