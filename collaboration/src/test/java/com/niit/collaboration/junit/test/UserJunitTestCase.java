package com.niit.collaboration.junit.test;


import static org.junit.Assert.assertEquals;

/*import org.apache.log4j.Logger;*/
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.*;
import com.niit.collaboration.model.User;

public class UserJunitTestCase {
	
	
	
	@Autowired
	BlogDao blogDao;
	
	//@Autowired
	//User user;		//instance of Blog created...
	
	AnnotationConfigApplicationContext context;		//instance created successfully...
	
	//Initialize test case...
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();	//object of AnnotationConfigApplicationContext created...
		context.scan("com.niit");	//scan base package of the application...
		context.refresh();		//referesh the application...
		
		blogDao = (BlogDao) context.getBean("blogDao");
		//user = (User) context.getBean("user");
		
	}
	
	@Test
	public void listBlog() {
		System.out.println("Testing user list");
		assertEquals(blogDao.getAllBlogs().size(), 3);
	}


}
