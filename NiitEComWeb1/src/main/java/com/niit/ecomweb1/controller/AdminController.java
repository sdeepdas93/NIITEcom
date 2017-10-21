package com.niit.ecomweb1.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.ProductBrandDao;
import com.niit.ecomweb1.dao.ProductCategoryDao;
import com.niit.ecomweb1.dao.ProductDao;
import com.niit.ecomweb1.dao.ProductSubCategoryDao;
import com.niit.ecomweb1.dao.SupplierDao;
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductBrand;
import com.niit.ecomweb1.model.ProductCategory;
import com.niit.ecomweb1.model.ProductSubCategory;
import com.niit.ecomweb1.model.Supplier;

@Controller
public class AdminController {
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	@Autowired 
	ProductSubCategoryDao productSubCategoryDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductBrandDao productBrandDao;
	@Autowired 
	SupplierDao supplierDao;
	
	
	
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
		
		
		@RequestMapping(value="/deleteProductSubCategory/{productSubCategoryId}",method=RequestMethod.GET)
		public ModelAndView deleteProductSubCategory(@PathVariable int productSubCategoryId,HttpSession httpSession){
			ModelAndView modelAndView=new ModelAndView("adminProductSubCategoryView","command",new ProductSubCategory());
			ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubCategoryByProductSubCategoryId(productSubCategoryId);
			if(productSubCategoryDao.deleteProductSubCategory(productSubCategory)){
				
				modelAndView.addObject("successMessage", "ProductSubCategory deleted successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to delete ProductSubCategory");
				
			}
			ProductCategory  productCategory=(ProductCategory)httpSession.getAttribute("productCategory");
			httpSession.setAttribute("productSubCategories", productSubCategoryDao.getProductSubCategoriesByProductCategory(productCategory));	
			return modelAndView;
		}
		
		
		
		//**Products**//
		@RequestMapping(value="/adminProductView/{productSubCategoryId}",method=RequestMethod.GET)
		public ModelAndView adminProductView(@PathVariable int productSubCategoryId,HttpSession httpSession){
			System.out.println("at method");
			ProductSubCategory productSubCategory=productSubCategoryDao.getProductSubCategoryByProductSubCategoryId(productSubCategoryId);
			
			httpSession.setAttribute("productSubCategory",productSubCategory);
			
			
			
			ModelAndView modelAndView=new ModelAndView("adminProductView","command",new Product());
			List<Product> products =productDao.getProductsByProductSubCategory(productSubCategory);
			if(products!=null){
				httpSession.setAttribute("products", products);
			}else{
				modelAndView.addObject("errorMessage", "Failed to show products");
			}
			
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			List<Supplier> suppliers=supplierDao.getAllSuppliers();
			httpSession.setAttribute("suppliers", suppliers);
			return modelAndView;
		}
		
		
		
		
		@RequestMapping(value="/addProduct",method=RequestMethod.POST)
		public ModelAndView AddUser(@ModelAttribute("Product") Product product,HttpServletRequest request, 
				@RequestParam("productImageFile") MultipartFile productImageFile,HttpSession httpSession){ 
			System.out.println("at addProduct");
			ModelAndView modelAndView=new ModelAndView("adminProductView","command",new Product());
			ProductSubCategory productSubCategory=(ProductSubCategory)httpSession.getAttribute("productSubCategory");
			product.setProductSubCategory(productSubCategory);
			
			if(productDao.getProductByProductId(product.getProductId())==null){
				//code for uploadding the file
				
				byte fileBytes[];
				FileOutputStream fos = null;
				
				String fileName = "";
				String productImage = "";
				ServletContext context = request.getServletContext();
				String realContextPath = context.getRealPath("/");
				String productName = product.getProductName();
				if (productImageFile != null){
					fileName = realContextPath + "/resources/images/productimages/" + productName + ".jpg";
					productImage = "resources/images/productimages/" + productName + ".jpg";
					System.out.println("===" + fileName + "===");
					File fileobj = new File(fileName);
					try{
						fos = new FileOutputStream(fileobj);
						fileBytes = productImageFile.getBytes();
						fos.write(fileBytes);
					} catch(Exception e) {
						e.printStackTrace();
					}
				
					product.setProductImage(productImage);	
				}
				//ends
		    	productDao.insertProduct(product);
		    	
		    	modelAndView.addObject("successMessage","product inserted successfully");
	        
			}else {
				
				modelAndView.addObject("errorMessage","failed to insert product");
				
			}
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			List<Product> products =productDao.getProductsByProductSubCategory(productSubCategory);
			httpSession.setAttribute("products", products);
			List<Supplier> suppliers=supplierDao.getAllSuppliers();
			httpSession.setAttribute("suppliers", suppliers);
			return modelAndView;
			
			
		}
		
		
		//deleteProduct
		@RequestMapping(value="/deleteProduct/{productId}",method=RequestMethod.GET)
		public ModelAndView deleteProduct(@PathVariable int productId,HttpSession httpSession){
			ModelAndView modelAndView=new ModelAndView("adminProductView","command",new Product());
			ProductSubCategory productSubCategory=(ProductSubCategory) httpSession.getAttribute("productSubCategory");
			Product product=productDao.getProductByProductId(productId);
			if(productDao.deleteProduct(product)){
				
				modelAndView.addObject("successMessage", "Product deleted successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to delete Product");
				
			}
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			List<Product> products =productDao.getProductsByProductSubCategory(productSubCategory);
			httpSession.setAttribute("products", products);
			List<Supplier> suppliers=supplierDao.getAllSuppliers();
			httpSession.setAttribute("suppliers", suppliers);
			return modelAndView;
		}
		
		//activateProduct
		@RequestMapping(value="/activateProduct/{productId}",method=RequestMethod.GET)
		public ModelAndView activateProduct(@PathVariable int productId,HttpSession httpSession){
			ModelAndView modelAndView=new ModelAndView("adminProductView","command",new Product());
			ProductSubCategory productSubCategory=(ProductSubCategory) httpSession.getAttribute("productSubCategory");
			Product product=productDao.getProductByProductId(productId);
			product.setProductStatus(true);
			if(productDao.updateProduct(product)){
				
				modelAndView.addObject("successMessage", "Product activated successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to activate Product");
				
			}
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			List<Product> products =productDao.getProductsByProductSubCategory(productSubCategory);
			httpSession.setAttribute("products", products);
			List<Supplier> suppliers=supplierDao.getAllSuppliers();
			httpSession.setAttribute("suppliers", suppliers);
			return modelAndView;
		}
		
		
		@RequestMapping(value="/deactivateProduct/{productId}",method=RequestMethod.GET)
		public ModelAndView deactivateProduct(@PathVariable int productId,HttpSession httpSession){
			ModelAndView modelAndView=new ModelAndView("adminProductView","command",new Product());
			ProductSubCategory productSubCategory=(ProductSubCategory) httpSession.getAttribute("productSubCategory");
			Product product=productDao.getProductByProductId(productId);
			product.setProductStatus(false);
			if(productDao.updateProduct(product)){
				
				modelAndView.addObject("successMessage", "Product deactivated successfully");
			}else{
				modelAndView.addObject("errorMessage", "Failed to deactivate Product");
				
			}
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			List<Product> products =productDao.getProductsByProductSubCategory(productSubCategory);
			httpSession.setAttribute("products", products);
			List<Supplier> suppliers=supplierDao.getAllSuppliers();
			httpSession.setAttribute("suppliers", suppliers);
			return modelAndView;
		}
		
		
		@RequestMapping(value="/adminProductUpdateView/{productId}",method=RequestMethod.GET)
		public ModelAndView adminProductUpdateview(@PathVariable int productId,HttpSession httpSession){
			Product product=productDao.getProductByProductId(productId);
			httpSession.setAttribute("product", product);
			ModelAndView modelAndView=new ModelAndView("adminProductUpdateView","command",product);
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			return modelAndView;
			
		}
		
		
		
		
		
		@RequestMapping(value="/updateProduct",method=RequestMethod.POST)
		public ModelAndView updateProduct(@ModelAttribute("Product") Product product,HttpServletRequest request, 
				@RequestParam("productImageFile") MultipartFile productImageFile,HttpSession httpSession){ 
			System.out.println("at updateProduct"+product.getProductId());
			ModelAndView modelAndView=new ModelAndView("adminProductView","command",new Product());
			ProductSubCategory productSubCategory=(ProductSubCategory)httpSession.getAttribute("productSubCategory");
			/*int productId=((Product)httpSession.getAttribute("product")).getProductId();
			product.setProductId(productId);*/
			
				//code for uploadding the file
				
				byte fileBytes[];
				FileOutputStream fos = null;
				
				String fileName = "";
				String productImage = "";
				ServletContext context = request.getServletContext();
				String realContextPath = context.getRealPath("/");
				String productName = product.getProductName();
				if (productImageFile != null){
					fileName = realContextPath + "/resources/images/productimages/" + productName + ".jpg";
					productImage = "resources/images/productimages/" + productName + ".jpg";
					System.out.println("===" + fileName + "===");
					File fileobj = new File(fileName);
					try{
						fos = new FileOutputStream(fileobj);
						fileBytes = productImageFile.getBytes();
						fos.write(fileBytes);
					} catch(Exception e) {
						e.printStackTrace();
					}
				
					product.setProductImage(productImage);	
				
				//ends
		    	productDao.updateProduct(product);
		    	
		    	modelAndView.addObject("successMessage","product updated successfully");
	        
			}else {
				
				modelAndView.addObject("errorMessage","failed to update product");
				
			}
			List<ProductBrand> productBrands =productBrandDao.getAllProductBrands();
			httpSession.setAttribute("productBrands",productBrands);
			List<Product> products =productDao.getProductsByProductSubCategory(productSubCategory);
			httpSession.setAttribute("products", products);
			List<Supplier> suppliers=supplierDao.getAllSuppliers();
			httpSession.setAttribute("suppliers", suppliers);
			return modelAndView;
			
			
		}
		
		
}
