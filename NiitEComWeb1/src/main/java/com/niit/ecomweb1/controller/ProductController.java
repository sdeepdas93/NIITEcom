package com.niit.ecomweb1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.*;
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

	@RequestMapping("/productCategoryView")
	public ModelAndView prductCategoryView(HttpSession httpSession){
		ModelAndView mv=new ModelAndView("productCategoryView");
		//List<ProductCategory> productCategories=productCategoryDao.getAllProductCategorys();
		//System.out.println("im here");
		/*for(ProductCategory productCategory:productCategories)
			System.out.println(productCategory.getProductCategoryName());*/
		//mv.addObject("productCategories", productCategoryDao.getAllProductCategorys());
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		mv.addObject("productBrands",productBrandDao.getAllProductBrands());
		
		for(ProductBrand productBrand: productBrandDao.getAllProductBrands())
			System.out.println(productBrand.getProductBrandName());
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
		
		
		
		return mv;
		
	}
	
	//viewProductsByProductSubCategory
	@RequestMapping(value="viewProductsByProductSubCategory/{productSubCategoryId}",method=RequestMethod.GET)
	
	public ModelAndView viewProductsByProductSubCategory(@PathVariable int productSubCategoryId, HttpSession httpSession){
		ModelAndView mv=new ModelAndView("viewProductsByProductSubCategory");
		
		ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubcategoryByProductSubCategoryId(productSubCategoryId);
		httpSession.setAttribute("productSubCategory", productSubCategory);
		mv.addObject("products", productDao.getProductsByProductSubCategory(productSubCategory));
		return mv;
		
	}
	
	//will be modified
	@RequestMapping(value="productDetailsView")
	public ModelAndView productDetailsView(){
		ModelAndView mv=new ModelAndView("productDetailsView");
		return mv;
	}
	
}
