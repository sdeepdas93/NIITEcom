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
import com.niit.ecomweb1.model.Supplier;

@Repository("supplierDao")
@Transactional
public class SupplierDaoImpl implements SupplierDao {
	
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


	public boolean insertSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	


	public List<Supplier> getAllSuppliers() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Supplier");
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
