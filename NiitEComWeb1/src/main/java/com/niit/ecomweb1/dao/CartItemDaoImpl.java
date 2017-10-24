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
import com.niit.ecomweb1.model.CartItem;
@Repository("cartItemDao")
@Transactional
public class CartItemDaoImpl implements CartItemDao {
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

	public boolean insertCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(cartItem);
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
		}
		
	}

	public boolean deletecartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(cartItem);
			session.flush();
			session.close();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			
		}
		
	}

	public boolean updatecartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(cartItem);
			
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
		
	}

	public List<CartItem> getAllCartItems() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("From CartItem");
			return query.list();
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<CartItem> getCartItemsByCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("From CartItem where cart.cartId = ?");
			query.setInteger(0, cart.getCartId());
			
			return query.list();
			
			
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}finally {
			session.flush();
			session.close();
			
		}
	}

	public CartItem getCartItembyCartItemId(int cartItemId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("From CartItem where cartItemId =?");
			query.setParameter(0, cartItemId);
			
			return (CartItem) query.uniqueResult();
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
