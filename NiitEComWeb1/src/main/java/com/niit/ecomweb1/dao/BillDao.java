package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Bill;
import com.niit.ecomweb1.model.Order;
import com.niit.ecomweb1.model.User;

public interface BillDao {
	public boolean insertBill(Bill bill);
	public boolean deleteBill(Bill bill);
	public boolean updateBill(Bill bill);
	public List<Bill> getAllBills();
	public Bill getBillByOrder(Order order);
	public List<Bill> getBillByUser(User user);
	
}
