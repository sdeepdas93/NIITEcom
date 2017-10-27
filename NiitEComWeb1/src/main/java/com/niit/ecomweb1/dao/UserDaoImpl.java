package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.OrderDetails;
import com.niit.ecomweb1.model.ProductReview;
import com.niit.ecomweb1.model.User;
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
		
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession(){
		return sessionFactory.openSession();
	}

	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(user);
			return true;
			}catch(HibernateException e){
			e.printStackTrace();
			return false;
			}
		finally {
			session.flush();
			session.close();
		}
		
	}

	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(user);
			return true;
			}catch(HibernateException e){
			e.printStackTrace();
			return false;
			}
		finally {
			session.flush();
			session.close();
		}
	}

	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(user);
			return true;
			}catch(HibernateException e){
			e.printStackTrace();
			return false;
			}
		finally {
			session.flush();
			session.close();
		}
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByProductReview(ProductReview productReview) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByCart(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User getUserByUserId(String userId){
		
		Session session=getSession();
		try{
			Query query=session.createQuery("from User where userId =?");
			query.setString(0, userId);
			return (User) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}finally {
				session.flush();
				session.close();
			}
	}
	
}
