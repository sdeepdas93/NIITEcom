package com.niit.ecomweb1.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.CartDao;
import com.niit.ecomweb1.dao.CartItemDao;
import com.niit.ecomweb1.dao.ProductBrandDao;
import com.niit.ecomweb1.dao.ProductCategoryDao;
import com.niit.ecomweb1.dao.ProductDao;
import com.niit.ecomweb1.dao.ProductSubCategoryDao;
import com.niit.ecomweb1.dao.UserDao;
import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.CartItem;
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.User;

@Controller
public class CartController {
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
	@Autowired
	CartDao cartDao;
	@Autowired
	CartItemDao cartItemDao;
	
	//Principal principal
	@RequestMapping("/cartView")
	public ModelAndView cartView(Principal principal, HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("cartView");
		String userId=principal.getName();
		User user=userDao.getUserByUserId(userId);
		System.out.println("user:"+user.getUserName());
		Cart cart=user.getCart();
		
		
		List<CartItem> cartItems=cartItemDao.getCartItemsByCart(cart);
		System.out.println("cartItems is null"+(cartItems==null));
		httpSession.setAttribute("cartItems", cartItems);
		double total=0;
		//CartItem cartItem2;
		if(cartItems!=null){
			
			for(CartItem cartItem:cartItems){
				total+=cartItem.getCartItemSubtotal();
				//cartItem2=cartItem;
			}
			cart.setCartTotal(total);
			System.out.println("*************************cartid"+cart.getCartId());
			cartDao.updateCart(cart);
		}
		httpSession.setAttribute("cart", cart);
		return modelAndView;
				
	}
	
	
	/*@RequestMapping("/dangerstate")
	ModelAndView dangerstate(HttpSession httpSession){
		System.out.println("at danger state");
		ModelAndView modelAndView=new ModelAndView("redirect:/cartView");
		Cart cart=(Cart) httpSession.getAttribute("cart");
		cartDao.updateCart(cart);
		
		return modelAndView;
	}*/
	
	
	@RequestMapping("/addToCart/{productId}")
	public ModelAndView addToCart(@PathVariable int productId, Principal principal, HttpSession httpSession){
		System.out.println("at addToCart");
		ModelAndView modelAndView=new ModelAndView("redirect:/cartView");
		Product product=productDao.getProductByProductId(productId);
		String userId=principal.getName();
		User user=userDao.getUserByUserId(userId);
		Cart cart=user.getCart();
		List<CartItem> cartItems=cartItemDao.getCartItemsByCart(cart);
		if(cartItems!=null){
			
			for(CartItem cartItem:cartItems){
				if(cartItem.getProduct().getProductId()==productId){
					int cartItemQuantity=cartItem.getCartItemQuantity()+1;
					double productPrice=cartItem.getProduct().getProductPrice();
					double cartItemSubtotal=cartItem.getCartItemSubtotal()+productPrice;
					cartItem.setCartItemQuantity(cartItemQuantity);
					cartItem.setCartItemSubtotal(cartItemSubtotal);
					cartItemDao.updatecartItem(cartItem);
					
					//cart.setCartTotal(cart.getCartTotal()+productPrice);
					//cartDao.updateCart(cart);
					
					return modelAndView;
					
				}
			}
		}else{
			System.out.println("cartitem null ********************************************* at add product");
		}
		
		CartItem cartItem=new CartItem();
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		cartItem.setCartItemQuantity(1);
		cartItem.setCartItemSubtotal(product.getProductPrice());
		cartItemDao.insertCartItem(cartItem);
		
		//cart.setCartTotal(cart.getCartTotal()+product.getProductPrice());
		//cartDao.updateCart(cart);
		
		return modelAndView;
		
		
	}
	
	@RequestMapping("/deleteCartItem/{cartItemId}")
	public ModelAndView deleteCartItem(@PathVariable int cartItemId, Principal principal, HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("redirect:/cartView");
		CartItem cartItem=cartItemDao.getCartItembyCartItemId(cartItemId);
		Cart cart=cartItem.getCart();
		cart.setCartTotal(cart.getCartTotal()-cartItem.getCartItemSubtotal());
		
		System.out.println("carttotal from delete cart"+cart.getCartTotal());
		cartItemDao.deletecartItem(cartItem);
		//cartDao.updateCart(cart);
		return modelAndView;
		
		
	}
	
	

}
