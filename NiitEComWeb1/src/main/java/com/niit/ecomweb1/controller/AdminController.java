package com.niit.ecomweb1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.ProductCategoryDao;
import com.niit.ecomweb1.model.ProductBrand;
import com.niit.ecomweb1.model.ProductCategory;

@Controller
public class AdminController {
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	
	@RequestMapping("/adminProductCategoryView")
	public ModelAndView adminProductCategoryView(HttpSession httpSession){
		ModelAndView mv=new ModelAndView("adminProductCategoryView","command",new ProductCategory());
		
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		System.out.println(productCategoryDao.getAllProductCategorys());
		return mv;
		
		
	}
	
	//@RequestMapping()

}
