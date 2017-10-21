package com.niit.ecomweb1.dao;

import java.util.List;

import com.niit.ecomweb1.model.ProductReview;
import com.niit.ecomweb1.model.User;

public interface UserDao {
	public boolean insertUser(User user);
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	public List<User> getAllUsers();
	public User getUserByProductReview(ProductReview productReview);
	public User getUserByCart(User user);
	public User getUserByUserId(String userId);

}
