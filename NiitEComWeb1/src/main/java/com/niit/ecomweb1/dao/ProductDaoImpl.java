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
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductBrand;
import com.niit.ecomweb1.model.ProductSubCategory;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {
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


	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
			Session session=getSession();
			try{
			
			session.save(product);
			return true;
			}catch(HibernateException e){
				e.printStackTrace();
				return false;
			}finally {
				session.flush();
				session.close();
				
			}
			
		}
	

	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.delete(product);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
		
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.update(product);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
		
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Product");
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> getProdctsByProductBrand(ProductBrand productBrand) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getProductsByProductSubCategory(ProductSubCategory productSubCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product getProductByCartitem(CartItem cartItem) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product getProductByProductId(int productId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Product where productId = ?");
			query.setInteger(0, productId);
			return(Product) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
		
	}

}
