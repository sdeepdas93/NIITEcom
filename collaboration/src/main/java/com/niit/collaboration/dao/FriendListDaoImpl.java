package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.FriendList;
@Repository("friendListDao")
@Transactional
public class FriendListDaoImpl implements FriendListDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public FriendListDaoImpl(SessionFactory sessionFactory){
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
		

	public boolean SavefriendList(FriendList friendList) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(friendList);
			session.flush();
			session.close();
			return true;
			
		}catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatefriendList(FriendList friendList) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(friendList);
			session.flush();
			session.close();
			return true;
			
		}catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deletefriendList(FriendList friendList){
		Session session=getSession();
		try{
			session.update(friendList);
			session.flush();
			session.close();
			return true;
			
		}catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public List<FriendList> getAllFriendListByUserId(String userId,String friendListStatus ) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try {
			Query query=session.createQuery("from FriendList where  userId = ? or friendId =? and friendListStatus = ?" );
			query.setString(0, userId);
			query.setString(1, userId);
			query.setString(2, friendListStatus);
			return query.list();
			
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public FriendList getFriendListByUsers(String userId1, String userId2,String friendListStatus){
		Session session= getSession();
		try{
			Query query=session.createQuery("from FriendList where (userId = :userId1 and friendId = userId2)"
					+ " or (userId = :userId2 and friendId = userId1) and friendListStatus = :friendListStatus");
			return (FriendList) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
