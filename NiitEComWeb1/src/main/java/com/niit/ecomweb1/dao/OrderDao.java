package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.Order;
import com.niit.ecomweb1.model.User;

public interface OrderDao {
	public boolean insertOrder(Order oder);
	public boolean deleteoder(Order oder);
	public boolean updateoder(Order oder);
	public List<Order> getAllOrders();
	public List<Order> getOrdersByUser(User user);
	public Order getOrderbyOrderId(int orderId);

}
