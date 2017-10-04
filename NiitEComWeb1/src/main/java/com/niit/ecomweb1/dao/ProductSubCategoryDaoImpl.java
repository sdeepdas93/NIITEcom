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

import com.niit.ecomweb1.model.ProductSubCategory;


@Repository("productSubCategoryDao")
@Transactional
public class ProductSubCategoryDaoImpl implements ProductSubCategoryDao {
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

	public boolean insertProductSubCategory(ProductSubCategory productSubCategory) {
		// TODO Auto-generated method stub
			Session session=getSession();
			try{
			
			session.save(productSubCategory);
			return true;
			}catch(HibernateException e){
				e.printStackTrace();
				return false;
			}finally {
				session.flush();
				session.close();
				
			}
		}

	public boolean deleteProductSubCategory(ProductSubCategory productSubCategory) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.delete(productSubCategory);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
	}

	public boolean updateProductSubCategory(ProductSubCategory productSubCategory) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.update(productSubCategory);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
	}
	public List<ProductSubCategory> getAllProductSubCategorys() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductSubCategory");
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductSubCategory> getProductSubCategoriesByProductCategory(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductSubCategory where productCategory.productCategoryId = :productCategoryId");
			query.setParameter("productCategoryId", productCategory.getProductCategoryId());
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public ProductSubCategory getProductSubcategoryByProductSubCategoryId(int productSubCategoryId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductSubCategory where productSubCategoryId = ?");
			query.setInteger(0, productSubCategoryId);
			return(ProductSubCategory) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
		
	}
}


