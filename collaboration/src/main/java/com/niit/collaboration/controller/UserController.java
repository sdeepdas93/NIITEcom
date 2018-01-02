package com.niit.collaboration.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.metamodel.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.niit.collaboration.dao.BlogDao;
import com.niit.collaboration.dao.UserDao;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.FileInfo;
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
		System.out.println("@edit user");
		if(userDao.getUserByUserId(userId)==null){
			user = new User();
			user.setErrorMessage("User does not exist with id : " +user.getUserId());
			
			
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
			
		}else{
			user.setUserId(userId);
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
	public ResponseEntity<User> logout(HttpSession session,HttpServletRequest request) {
		
		
		System.out.println("at user logout");
		System.out.println("***** path***"+request.getServletContext().getRealPath("/"));
	User user = (User) session.getAttribute("loggedInUser");
		System.out.println("**********is user null"+(user==null)+"**********");
		user.setUserIsOnline("OFFLINE");
		user=userDao.updateUser(user);
		
		user=new User();
		
		
		
		session.invalidate();
		
		return new ResponseEntity<User> (user,HttpStatus.OK);
	}
	
	
	//file uploads
	
	@PostMapping("/fileUpload")
	public Database continueFileUpload(HttpServletRequest request, HttpServletResponse response){
		System.out.println("at fileUpload");
	        MultipartHttpServletRequest mRequest;
		String filename = "upload1.jpg";
		try {
		   mRequest = (MultipartHttpServletRequest) request;
		   mRequest.getParameterMap();

		   Iterator<String> itr = mRequest.getFileNames();
		   while (itr.hasNext()) {
		        MultipartFile mFile = mRequest.getFile(itr.next());
		        String fileName = mFile.getOriginalFilename();
		        System.out.println(fileName);
		              System.out.println(request.getServletContext().getRealPath("/")+"/demoimg/" + filename);
		        java.nio.file.Path path = Paths.get(request.getServletContext().getRealPath("/")+"/demoimg/" + filename);
		        Files.deleteIfExists(path);
		        InputStream in = mFile.getInputStream();
		        Files.copy(in, path);
			 }
		   } catch (Exception e) {
		        e.printStackTrace();
		   }
		return null;
		}
	
	
	
	
	

	 @RequestMapping(value = "/fileupload/", method = RequestMethod.POST)
	 public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile,HttpServletRequest request) {
	  FileInfo fileInfo = new FileInfo();
	 
	  byte fileBytes[];
		FileOutputStream fos = null;
		
		String fileName = "";
		String productImage = "";
		ServletContext context = request.getServletContext();
		String realContextPath = context.getRealPath("/");
		String imageName = "demoimagename";
		if (inputFile != null){
				fileName = realContextPath + "/resources/images/demofolder/" + imageName + ".jpg";
				productImage = "resources/images/demofolder/" + imageName + ".jpg";
				System.out.println("===" + fileName + "===");
				File fileobj = new File(fileName);
				try{
					fos = new FileOutputStream(fileobj);
					fileBytes = inputFile.getBytes();
					fos.write(fileBytes);
				} catch(Exception e) {
					e.printStackTrace();
					fileInfo.setErrorCode("404");
					fileInfo.setErrorMessage("file not uploaded");
					return new ResponseEntity<FileInfo>(fileInfo,HttpStatus.NOT_FOUND);
				}
				fileInfo.setFileName(fileName);
				return new ResponseEntity<FileInfo>(fileInfo,HttpStatus.OK);
		 }
		else{
			
			fileInfo.setErrorCode("404");
			fileInfo.setErrorMessage("file not uploaded");
			return new ResponseEntity<FileInfo>(fileInfo,HttpStatus.NOT_FOUND);
		}
	 }

}
