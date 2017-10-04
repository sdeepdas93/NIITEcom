package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.Address;
import com.niit.ecomweb1.model.Order;
import com.niit.ecomweb1.model.Supplier;
import com.niit.ecomweb1.model.User;

@Repository("addressDao")
@Transactional
public class AddressDaoImpl implements AddressDao{
	
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

	

	public List<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Address");
			return query.list();
		}catch(HibernateException e){
			System.out.println(e.toString());
			return null;
		}
		
		
		
	}

	public boolean insertAddress(Address address) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(address);
			return true;
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public boolean deleteAddress(Address address) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(address);
			return true;
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}


	public boolean updateAddress(Address address) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(address);
			return true;
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public Address getAddressByAddressId(int addressId) {
		// TODO Auto-generated method stub
		try{
		Session session=getSession();
		Query query=session.createQuery("from Address addressId = ?");
		query.setInteger(0, addressId);
		return (Address) query.uniqueResult();
		}catch(HibernateException e){
			System.out.println(e.toString());
			return null;
		}
		
	}

}
