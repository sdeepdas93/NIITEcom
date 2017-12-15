package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogDao;
import com.niit.collaboration.dao.UserDao;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.User;

@RestController

public class UserController {
	@Autowired
	UserDao userDao;
	
	
	//http://localhost:8080/collaboration/
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> listUser() {
		
		List<User> users = userDao.getAllUsers();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value = "/activeUsers")
	public ResponseEntity<List<User>> listActiveUser() {
		
		List<User> users = userDao.getAllActiveUser();
		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/user/{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") String userId,HttpSession session){
		try{
			User loggedInUser=(User)session.getAttribute("loggedInUser");
			System.out.println(loggedInUser.getUserName()+" at get user by id");
			User user=userDao.getUserByUserId(userId);
			
			if(user!=null){
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}else{
				user=new User();
				user.setErrorCode("404");
				user.setErrorMessage("user not found");
				return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
			}
		}catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			User user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("not logged in");
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PostMapping(value = "/user/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if(userDao.getUserByUserId(user.getUserId())==null){
			System.out.println("at create user");
			user.setUserStatus("A");
			user.setUserRole("USER");
			user.setUserIsOnline("OFFLINE");
			user.setUserImage("demo image");
			user=userDao.saveUser(user);
			
//			if(user==null){
//				user.setErrorCode("404");
//				user.setErrorMessage("User Not Created");
//				
//			}
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}else{
			user.setErrorCode("404");
			user.setErrorMessage("User Not Created");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}
	
	/*@GetMapping(value = "/deactivetUser/{id}")
	public ResponseEntity<User> deactivetUser(@PathVariable("id") String userId, HttpSession session) {
		User user=userDao.getUserByUserId(userId);
		user.setUserStatus("D");
		user.setUserIsOnline("OFFLINE");
		userDao.updateUser(user);
		
		
	}*/
	
	//added on 12/08/2017
	@PutMapping(value="/activateUser/{userId}")
	public ResponseEntity<User> activateUser(@PathVariable("userId") String userId,HttpSession session){
		try{
				User user=userDao.getUserByUserId(userId);
				if((((User)session.getAttribute("loggedInUser")).getUserRole().equals("ADMIN_USER"))||
					(user!=null)){
					user.setUserStatus("A");
					userDao.updateUser(user);
					return new  ResponseEntity<User>(user,HttpStatus.OK);
				}else{
					user=new User();
					user.setErrorCode("404");
					user.setErrorMessage("failed to activate user");
					return new ResponseEntity<User>(user,HttpStatus.OK);
				}
			}catch(NullPointerException e){
			e.printStackTrace();
			User user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("Admin not logged in");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}
	
	@PutMapping(value="/deactivateUser/{userId}")
	public ResponseEntity<User> deactivateUser(@PathVariable("userId") String userId,HttpSession session){
		try{
				User user=userDao.getUserByUserId(userId);
				if((((User)session.getAttribute("loggedInUser")).getUserRole().equals("ADMIN_USER"))||
					(user!=null)){
					user.setUserStatus("D");
					userDao.updateUser(user);
					return new  ResponseEntity<User>(user,HttpStatus.OK);
				}else{
					user=new User();
					user.setErrorCode("404");
					user.setErrorMessage("failed to deactivate user");
					return new ResponseEntity<User>(user,HttpStatus.OK);
				}
			}catch(NullPointerException e){
			e.printStackTrace();
			User user=new User();
			user.setErrorCode("404");
			user.setErrorMessage("Admin not logged in");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
	}
	
	
	
	//need to be modified
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User user, HttpSession session) {
		if(userDao.getUserByUserId(userId)==null){
			user = new User();
			user.setErrorMessage("User does not exist with id : " +user.getUserId());
			
			
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
			
		}else{
			user=userDao.updateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		}
	}
	
	@PostMapping(value = "/user/login")
	public ResponseEntity<User> login(@RequestBody User user, HttpSession session) {
		
		user = userDao.authenticateUser(user);
		if(user==null){
			user = new User();	
			user.setErrorCode("404");
			user.setErrorMessage("Invalid userId or password...");
		}else if(user.getUserStatus().equals("D")){
			user = new User();	
			user.setErrorCode("404");
			user.setErrorMessage("user has been deactivated");
		}
			
		else {
			session.setAttribute("loggedInUser", user);
			/*session.setAttribute("loggedInUserID", user.getUserId());
			session.setAttribute("LoggedInStatus", user.getUserIsOnline());*/
			
			/*friendDAO.setOnline(users.getId());*/
			String userIsOnline="ONLINE";
			user.setUserIsOnline(userIsOnline);
			user=userDao.updateUser(user);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/logout")
	public ResponseEntity<User> logout(HttpSession session) {
		
		System.out.println("at user logout");
	User user = (User) session.getAttribute("loggedInUser");
		System.out.println("**********is user null"+(user==null)+"**********");
		user.setUserIsOnline("OFFLINE");
		user=userDao.updateUser(user);
		
		user=new User();
		
		
		
		session.invalidate();
		
		return new ResponseEntity<User> (user,HttpStatus.OK);
	}
}

