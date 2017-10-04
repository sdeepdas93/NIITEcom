package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductReview;

@Repository("productReviewDao")
@Transactional
public class ProductReviewDaoImpl implements ProductReviewDao {
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

	public boolean insertProductReview(ProductReview productReview) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.save(productReview);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
	}

	public boolean deleteProductReview(ProductReview productReview) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.delete(productReview);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
	}

	public boolean updateProductReview(ProductReview productReview) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
		
		session.update(productReview);
		return true;
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}finally {
			session.flush();
			session.close();
			
		}
	}

	public List<ProductReview> getAllProductReviews() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductReview");
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductReview> getProductReviewsByProduct(Product product) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductReview where product.productId = :productId");
			query.setParameter("productId", product.getProductId());
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public ProductReview getProductReviewByProductReviewId(int productReviewId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from ProductReview where productReviewId = ?");
			query.setInteger(0, productReviewId);
			return(ProductReview) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
			}
		
	}

}
