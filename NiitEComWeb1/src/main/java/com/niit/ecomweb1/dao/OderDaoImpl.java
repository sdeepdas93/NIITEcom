package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.Order;
import com.niit.ecomweb1.model.User;
import com.sun.org.apache.regexp.internal.recompile;
@Repository("orderDao")
@Transactional
public class OderDaoImpl implements OrderDao {
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

	public boolean insertOrder(Order oder) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(oder);
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

	public boolean deleteoder(Order oder) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(oder);
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

	public boolean updateoder(Order oder) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(oder);
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

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Order");
			return query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
	}

	public List<Order> getOrdersByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getOrderbyOrderId(int orderId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Order where orderId =?");
			query.setInteger(0, orderId);
			return (Order) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
	}
}


