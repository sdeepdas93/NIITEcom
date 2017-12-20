package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.BlogLike;
@Repository("blogLikeDao")
@Transactional
public class BlogLikeDaoImpl implements BlogLikeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public BlogLikeDaoImpl(SessionFactory sessionFactory){
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

	public List<BlogLike> getBlogLikesByBlogId(int blogId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		Query query=session.createQuery(" from BlogLike where blogId= ?");
		query.setInteger(0, blogId);
		
		return query.list();
		
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveBlogLike(BlogLike blogLike) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try {
			session.save(blogLike);
			session.flush();
			session.close();
			return true;
			
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public boolean isExist(int blogId, String userId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		Query query=session.createQuery(" from BlogLike where blogId= ? and userId = ?");
		query.setInteger(0, blogId);
		query.setString(1, userId);
		return !query.list().isEmpty();
		
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

}
