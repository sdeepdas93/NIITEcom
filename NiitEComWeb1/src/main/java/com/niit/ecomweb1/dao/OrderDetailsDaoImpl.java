package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.CartItem;
import com.niit.ecomweb1.model.OrderDetails;
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.User;
@Repository("orderDetailsDao")
@Transactional
public class OrderDetailsDaoImpl implements OrderDetailsDao {
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

	

	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetailsId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from OrderDetails where orderDetailsId = ?");
			query.setInteger(0, orderDetailsId);
			return(OrderDetails) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}finally {
				session.flush();
				session.close();
				
			}
	}

	public boolean updateOrderDetails(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(orderDetails);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public boolean insertOrderDetails(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(orderDetails);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public boolean deleteOrderDetails(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(orderDetails);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public List<OrderDetails> getOrderDetailsListByUser(User user) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from OrderDetails where user.userId = :userId");
			query.setParameter("userId", user.getUserId());
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}finally {
			session.flush();
			session.close();
			
		}
	}
	
	
	

}
