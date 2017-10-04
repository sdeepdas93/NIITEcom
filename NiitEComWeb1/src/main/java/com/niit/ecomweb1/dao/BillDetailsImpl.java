package com.niit.ecomweb1.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.ecomweb1.model.Bill;
import com.niit.ecomweb1.model.BillDetails;

@Repository("billDetailsDao")
@Transactional
public class BillDetailsImpl implements BillDetailsDao {
	
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

	public boolean insertBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(billDetails);
			return true;
			
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public boolean deleteBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(billDetails);
			return true;
			
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}
	
	

	public boolean updateBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(billDetails);
			return true;
			
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public List<BillDetails> getAllBillDetails() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from BillDetails");
			return query.list();
		}catch(HibernateException e){
			System.out.println(e.toString());
			return null;
		}
	}

	public BillDetails getBillDetailsByBill(Bill bill) {
		// TODO Auto-generated method stub
		return null;
	}

	public BillDetails getBillDetailsByBillDetailsId(int billDetailsId) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from BillDetails where billDetailsId = ?");
			return (BillDetails) query.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
		
		//query.setInteger(0, user.getUserId());
	}

}
