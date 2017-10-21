package com.niit.ecomweb1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.CartDao;
import com.niit.ecomweb1.dao.UserDao;
import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.ProductCategory;
import com.niit.ecomweb1.model.User;
import com.sun.org.apache.xpath.internal.operations.Mod;



@Controller
public class UserController {
	@Autowired
	UserDao userDao;
	@Autowired
	CartDao cartDao;
	
	
	
	
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage() {

		return new ModelAndView("loginPage");
	}
	
	
	@RequestMapping(value = "/userRegistrationView", method = RequestMethod.GET)
	public ModelAndView userRegistrationView(){
		return new ModelAndView("userRegistrationView","command",new User());
	}
	
	//registerUser
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("User") User user,HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("userRegistrationView","command",new User());
		if(userDao.getUserByUserId(user.getUserId())!=null){
			System.out.println("at same user Id");
			modelAndView.addObject("errorMessage","Please try different user Id.");
			return modelAndView;
			
		}else{
			user.setEnabled(true);
			user.setUserRole("ROLE_USER");
			Cart cart=new Cart();
			
			if(cartDao.insertCart(cart)){
				
				user.setCart(cart);
				if(userDao.insertUser(user)){
					modelAndView.addObject("successMessage","You have been registred successfully.");
					return modelAndView;
				}else{
					modelAndView.addObject("errorMessage","User has not been inserted!");
					return modelAndView;
				}
				
			}else{
				modelAndView.addObject("errorMessage","Cart has not been inserted!");
				return modelAndView;
			}
			
			
			
		}
		
		
	}
	
}
