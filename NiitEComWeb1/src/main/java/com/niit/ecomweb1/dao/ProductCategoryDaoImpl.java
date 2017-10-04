package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.ProductCategory;

@Repository("productCategoryDao")
@Transactional
public class ProductCategoryDaoImpl implements ProductCategoryDao {
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
	public boolean insertProductCategory(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(productCategory);
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

	public boolean deleteoder(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(productCategory);
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

	public boolean updateoder(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(productCategory);
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

	public List<ProductCategory> getAllProductCategorys() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductCategory");
			return query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
	}
	

	public ProductCategory getProductCategoryByProductCategoryId(int productCategoryId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductCategory where productCategoryId =?");
			query.setInteger(0, productCategoryId);
			return (ProductCategory) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
	}
}


