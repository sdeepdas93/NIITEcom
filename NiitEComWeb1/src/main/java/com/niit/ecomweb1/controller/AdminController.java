package com.niit.ecomweb1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.ProductCategoryDao;
import com.niit.ecomweb1.dao.ProductSubCategoryDao;
import com.niit.ecomweb1.model.ProductBrand;
import com.niit.ecomweb1.model.ProductCategory;
import com.niit.ecomweb1.model.ProductSubCategory;

@Controller
public class AdminController {
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	@Autowired 
	ProductSubCategoryDao productSubCategoryDao;
	
	
	
	@RequestMapping("/adminProductCategoryView")
	public ModelAndView adminProductCategoryView(HttpSession httpSession){
		ModelAndView mv=new ModelAndView("adminProductCategoryView","command",new ProductCategory());
		
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		System.out.println(productCategoryDao.getAllProductCategorys());
		return mv;
		
		
	}
	
	
	
	@RequestMapping(value="/addProductCategory",method=RequestMethod.POST)
	public ModelAndView addProductCategory(@ModelAttribute("ProductCategory") ProductCategory productCategory,HttpSession httpSession){
		
		ModelAndView modelAndView=new ModelAndView("adminProductCategoryView","command",new ProductCategory());
		if(productCategoryDao.insertProductCategory(productCategory)){
			modelAndView.addObject("successMessage", "ProductCategory added successfully");
		}else{
			modelAndView.addObject("errorMessage", "Failed to add ProductCategory");
		}
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());	
		return modelAndView;
		
	}
	
	@RequestMapping(value="/activateProductCategory/{productCategoryId}",method=RequestMethod.GET)
	public ModelAndView activateProductCategory(@PathVariable int productCategoryId, HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("adminProductCategoryView","command",new ProductCategory());
		ProductCategory productCategory=productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
		productCategory.setProductCategoryStatus(true);
		if(productCategoryDao.updateProductCategory(productCategory)){
			modelAndView.addObject("successMessage", "ProductCategory activated successfully");
		}else{
			modelAndView.addObject("errorMessage", "Failed to activate ProductCategory");
		}
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		return modelAndView;
	}
	
	@RequestMapping(value="/deactivateProductCategory/{productCategoryId}",method=RequestMethod.GET)
	public ModelAndView deactivateProductCategory(@PathVariable int productCategoryId,HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("adminProductCategoryView","command",new ProductCategory());
		ProductCategory productCategory=productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
		productCategory.setProductCategoryStatus(false);
		if(productCategoryDao.updateProductCategory(productCategory)){
			modelAndView.addObject("successMessage", "ProductCategory deactivated successfully");
		}else{
			modelAndView.addObject("errorMessage", "Failed to deactivate ProductCategory");
		}
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		return modelAndView;
	}
    
	
	@RequestMapping(value="/deleteProductCategory/{productCategoryId}",method=RequestMethod.GET)
	public ModelAndView deleteProductCategory(@PathVariable int productCategoryId,HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("adminProductCategoryView","command",new ProductCategory());
		ProductCategory productCategory=productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
		if(productCategoryDao.deleteProductCategory(productCategory)){
			
			modelAndView.addObject("successMessage", "ProductCategory deleted successfully");
		}else{
			modelAndView.addObject("errorMessage", "Failed to delete ProductCategory");
			
		}
		httpSession.setAttribute("productCategories", productCategoryDao.getAllProductCategorys());
		return modelAndView;
	}
	
	
	
	//*******************ProductSubCategorySection**********************
	
	
	@RequestMapping(value="/adminProductSubCateGoryView/{productCategoryId}",method=RequestMethod.GET)
	public ModelAndView adminProductSubCateGoryView(@PathVariable int productCategoryId,HttpSession httpSession){
		
		
		
		
		
		
		ProductCategory productCategory=productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
		
		httpSession.setAttribute("productCategory",productCategory);
		
		
		
		ModelAndView modelAndView=new ModelAndView("adminProductSubCategoryView","command",new ProductSubCategory());
		List<ProductSubCategory> productSubCategories =productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory);
		if(productSubCategories!=null){
			httpSession.setAttribute("productSubCategories", productSubCategories);
		}else{
			modelAndView.addObject("errorMessage", "Failed to show productSubCategories");
		}
		return modelAndView;
	}
	
	
	
	//addProductSubCategory
		@RequestMapping(value="/addProductSubCategory",method=RequestMethod.POST)
		public ModelAndView addProductSubCategory(@ModelAttribute("ProductSubCategory") ProductSubCategory productSubCategory,HttpSession httpSession){
			
			ProductCategory  productCategory=(ProductCategory)httpSession.getAttribute("productCategory");
			System.out.println("kkkkk "+productCategory.getProductCategoryId());
			productSubCategory.setProductCategory(productCategory);
			
			//ProductCategory  p=productCategoryDao.getProductCategoryByProductCategoryId(productCategoryId);
			ModelAndView modelAndView=new ModelAndView("adminProductSubCategoryView","command",new ProductSubCategory());
			if(productSubCategoryDao.insertProductSubCategory(productSubCategory)){
				modelAndView.addObject("successMessage", "ProductSubCategory added successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to add ProductSubCategory");
			}
			httpSession.setAttribute("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));	
			return modelAndView;
			
		}
		
		//activateProductSubCategory
		@RequestMapping(value="/activateProductSubCategory/{productSubCategoryId}",method=RequestMethod.GET)
		public ModelAndView activateProductSubCategory(@PathVariable int productSubCategoryId, HttpSession httpSession){
			ModelAndView modelAndView=new ModelAndView("adminProductSubCategoryView","command",new ProductSubCategory());
			ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubCategoryByProductSubCategoryId(productSubCategoryId);
			productSubCategory.setProductSubCategoryStatus(true);
			if(productSubCategoryDao.updateProductSubCategory(productSubCategory)){
				modelAndView.addObject("successMessage", "ProductSubCategory activated successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to activate ProductSubCategory");
			}
			ProductCategory  productCategory=(ProductCategory)httpSession.getAttribute("productCategory");
			httpSession.setAttribute("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));	
			return modelAndView;
		}
		
		
		@RequestMapping(value="/deactivateProductSubCategory/{productSubCategoryId}",method=RequestMethod.GET)
		public ModelAndView deactivateProductSubCategory(@PathVariable int productSubCategoryId, HttpSession httpSession){
			ModelAndView modelAndView=new ModelAndView("adminProductSubCategoryView","command",new ProductSubCategory());
			ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubCategoryByProductSubCategoryId(productSubCategoryId);
			productSubCategory.setProductSubCategoryStatus(false);
			if(productSubCategoryDao.updateProductSubCategory(productSubCategory)){
				modelAndView.addObject("successMessage", "ProductSubCategory deactivated successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to deactivate ProductSubCategory");
			}
			ProductCategory  productCategory=(ProductCategory)httpSession.getAttribute("productCategory");
			httpSession.setAttribute("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));	
			return modelAndView;
		}
		
}
