package com.niit.ecomweb1.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.*;
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductBrand;
import com.niit.ecomweb1.model.ProductCategory;
import com.niit.ecomweb1.model.ProductSubCategory;
@Controller
public class ProductController {
	@Autowired
	ProductBrandDao productBrandDao;
	@Autowired
	ProductCategoryDao productCategoryDao;
	@Autowired
	ProductSubCategoryDao productSubCategoryDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/")
	public ModelAndView startView(){
		
		ModelAndView mv=new ModelAndView("redirect:/productCategoryView");
		return mv;
	}

	@RequestMapping("/productCategoryView")
	public ModelAndView prductCategoryView(HttpSession httpSession){
		System.out.println("httpSession");
		ModelAndView mv=new ModelAndView("productCategoryView");
		//List<ProductCategory> productCategories=productCategoryDao.getAllProductCategorys();
		//System.out.println("im here");
		/*for(ProductCategory productCategory:productCategories)
			System.out.println(productCategory.getProductCategoryName());*/
		//mv.addObject("productCategories", productCategoryDao.getAllProductCategorys());
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		httpSession.setAttribute("products", productDao.getAllProducts());
		mv.addObject("productBrands",productBrandDao.getAllProductBrands());
		
		
		return mv;
		
		
	}
	
	
	
	//productSubCategoryView
	@RequestMapping(value="productSubCategoryView/{productCategoryId}",method=RequestMethod.GET)
	
	public ModelAndView productSubCategoryView(@PathVariable int productCategoryId, HttpSession httpSession){
		ModelAndView mv=new ModelAndView("productSubCategoryView");
		ProductCategory productCategory= productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
		//mv.addObject("productCategory", productCategory);
		httpSession.setAttribute("productCategory", productCategory);
		mv.addObject("productBrands",productBrandDao.getAllProductBrands());
		httpSession.setAttribute("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));
		//mv.addObject("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));
		
		List<ProductSubCategory> productSubCategories=productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory);
		List<Product> products =new ArrayList<Product>();
		for(ProductSubCategory productSubCategory: productSubCategories){
			
				products.addAll(productDao.getProductsByProductSubCategory(productSubCategory));
			
			
		}
		
		httpSession.setAttribute("products", products);
		return mv;
		
	}
	
	//viewProductsByProductSubCategory
	@RequestMapping(value="viewProductsByProductSubCategory/{productSubCategoryId}",method=RequestMethod.GET)
	
	public ModelAndView viewProductsByProductSubCategory(@PathVariable int productSubCategoryId, HttpSession httpSession){
		ModelAndView mv=new ModelAndView("viewProductsByProductSubCategory");
		
		ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubCategoryByProductSubCategoryId(productSubCategoryId);
		httpSession.setAttribute("productSubCategory", productSubCategory);
		mv.addObject("products", productDao.getProductsByProductSubCategory(productSubCategory));
		return mv;
		
	}
	
	//will be modified
	@RequestMapping(value="viewProductDetails/{productId}",method=RequestMethod.GET)
	public ModelAndView productDetailsView(@PathVariable int productId,HttpSession httpSession){
		ModelAndView mv=new ModelAndView("productDetailsView");
		Product product=productDao.getProductByProductId(productId);
		httpSession.setAttribute("product",product);
		return mv;
	}
	
	
	
	//for users and admin
	
	@RequestMapping("/userProductCategoryView")
	public ModelAndView userPrductCategoryView(HttpSession httpSession,Principal principal){
		ModelAndView mv=new ModelAndView("productCategoryView");
		//List<ProductCategory> productCategories=productCategoryDao.getAllProductCategorys();
		//System.out.println("im here");
		/*for(ProductCategory productCategory:productCategories)
			System.out.println(productCategory.getProductCategoryName());*/
		//mv.addObject("productCategories", productCategoryDao.getAllProductCategorys());
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		mv.addObject("productBrands",productBrandDao.getAllProductBrands());
		String userId=principal.getName();
		httpSession.setAttribute("user", userDao.getUserByUserId(userId));
		return mv;
		
		
	}
	
	
	
	//productSubCategoryView
	@RequestMapping(value="userProductSubCategoryView/{productCategoryId}",method=RequestMethod.GET)
	
	public ModelAndView userProductSubCategoryView(@PathVariable int productCategoryId, HttpSession httpSession){
		ModelAndView mv=new ModelAndView("productSubCategoryView");
		ProductCategory productCategory= productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
		//mv.addObject("productCategory", productCategory);
		httpSession.setAttribute("productCategory", productCategory);
		mv.addObject("productBrands",productBrandDao.getAllProductBrands());
		httpSession.setAttribute("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));
		//mv.addObject("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));
		
		
		
		return mv;
		
	}
	
	//viewProductsByProductSubCategory
	@RequestMapping(value="userViewProductsByProductSubCategory/{productSubCategoryId}",method=RequestMethod.GET)
	
	public ModelAndView userViewProductsByProductSubCategory(@PathVariable int productSubCategoryId, HttpSession httpSession){
		ModelAndView mv=new ModelAndView("viewProductsByProductSubCategory");
		
		ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubCategoryByProductSubCategoryId(productSubCategoryId);
		httpSession.setAttribute("productSubCategory", productSubCategory);
		mv.addObject("products", productDao.getProductsByProductSubCategory(productSubCategory));
		return mv;
		
	}
	
	//will be modified
	/*@RequestMapping(value="userProductDetailsView")
	public ModelAndView userProductDetailsView(Principal principal,HttpSession httpSession){
		ModelAndView mv=new ModelAndView("productDetailsView");
		Us
		
		return mv;
	}
	*/
	
	
	
}
