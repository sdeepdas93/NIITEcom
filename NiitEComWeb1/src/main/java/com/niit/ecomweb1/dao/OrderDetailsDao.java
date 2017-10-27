package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.OrderDetails;
import com.niit.ecomweb1.model.User;

public interface OrderDetailsDao {
public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetailsId);
public boolean updateOrderDetails(OrderDetails orderDetails);
public boolean insertOrderDetails(OrderDetails orderDetails);
public boolean deleteOrderDetails(OrderDetails orderDetails);
public List<OrderDetails> getOrderDetailsListByUser(User user);
}
