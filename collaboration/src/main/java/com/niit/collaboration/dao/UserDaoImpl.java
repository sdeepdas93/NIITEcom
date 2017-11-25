package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	

	public UserDaoImpl(SessionFactory sessionFactory){
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

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from User");
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(user);
			System.out.println("AT SAVE USER RETURNNG USER");
			session.flush();
			session.close();
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
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
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(user);
			session.flush();
			session.close();
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from User where userId = ?");
			query.setString(0, userId);
			return (User) query.uniqueResult();
			
		
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}

	}
		
	

	public User authenticateUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from User where userId = ? and userPassword = ? and userStatus = 'A'");
			query.setString(0, user.getUserId());
			query.setString(1, user.getUserPassword());
			return (User) query.uniqueResult();
			
		
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
	}

	public List<User> getAllActiveUser() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from User where userStatus = ?");
			query.setString(0, "A");
			return query.list();
			
		
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}

	}
}
