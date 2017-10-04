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
import com.niit.ecomweb1.model.Order;
import com.niit.ecomweb1.model.User;


@Repository("billDao")
@Transactional
public class BillDaoImpl implements BillDao {
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


	public boolean insertBill(Bill bill) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.save(bill);
			return true;
			
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public boolean deleteBill(Bill bill) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.delete(bill);
			return true;
			
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public boolean updateBill(Bill bill) {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			session.update(bill);
			return true;
			
		}catch(HibernateException e){
			System.out.println(e.toString());
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		Session session=getSession();
		try{
			Query query=session.createQuery("from Bill");
			return query.list();
		}catch(HibernateException e){
			System.out.println(e.toString());
			return null;
		}
	}
	//***
	public Bill getBillByOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<Bill> getBillByUser(User user) {
		// TODO Auto-generated method stub
		return null;
		
	}

}
