package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.User;
@Repository("cartDaoImpl")
@Transactional

public class CartIDaoImpl implements CartDao {
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

	public boolean insertCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			
			session.save(cart);
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

	public boolean deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			
			session.delete(cart);
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

	public boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			
			session.update(cart);
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

	public List<Cart> getAllCart() {
		// TODO Auto-generated method stub
		try{
			Session session=getSession();
			Query query=session.createQuery("from Cart");
			
			
			
			return query.list();
			}catch(HibernateException e){
				System.out.println(e.toString());
				return null;
			}
		
	}

	public Cart getCartByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cart getCartByCartId(int cartId) {
		// TODO Auto-generated method stub
		try{
			Session session=getSession();
			Query query=session.createQuery("from Cart where cartId = ?");
			query.setInteger(0,cartId);
			return (Cart) query.uniqueResult();
			
			}catch(HibernateException e){
				System.out.println(e.toString());
				return null;
			}
		
	}
}
