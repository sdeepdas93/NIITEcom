package com.niit.ecomweb1.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ecomweb1.dao.CartDao;
import com.niit.ecomweb1.dao.CartItemDao;
import com.niit.ecomweb1.dao.OrderDetailsDao;
import com.niit.ecomweb1.dao.ProductBrandDao;
import com.niit.ecomweb1.dao.ProductCategoryDao;
import com.niit.ecomweb1.dao.ProductDao;
import com.niit.ecomweb1.dao.ProductSubCategoryDao;
import com.niit.ecomweb1.dao.UserDao;
import com.niit.ecomweb1.model.Cart;
import com.niit.ecomweb1.model.CartItem;
import com.niit.ecomweb1.model.OrderDetails;
import com.niit.ecomweb1.model.Product;
import com.niit.ecomweb1.model.ProductCategory;
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
	@Autowired
	OrderDetailsDao orderDetailsDao;
	
	
	@Autowired
	private JavaMailSender mailSender; 
	
	//Principal principal
	@RequestMapping("/cartView")
	public ModelAndView cartView(Principal principal, HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("cartView","command",new OrderDetails());
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
					cartItemDao.updateCartItem(cartItem);
					
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
		cartItem.setCartItemStatus("NOTORDERRED");
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
		cartItemDao.deleteCartItem(cartItem);
		//cartDao.updateCart(cart);
		return modelAndView;
		
		
	}
	
	
	@RequestMapping(value="/addToOrderDetails",method=RequestMethod.POST)
	public ModelAndView addToOrderDetails(@ModelAttribute("OrderDetails") OrderDetails orderDetails,Principal principal,HttpSession httpSession){
		System.out.println("at addToOrderDetails");
		ModelAndView modelAndView=new ModelAndView("redirect:/orderDetailsView");
		User user=userDao.getUserByUserId(principal.getName());
		orderDetails.setUser(user);
		Date d=new Date(System.currentTimeMillis());
		Cart cart=user.getCart();
		orderDetails.setOrderDetailsTotal(cart.getCartTotal());
		orderDetails.setOrderDetailsDate(d);
		orderDetailsDao.insertOrderDetails(orderDetails);
		List<CartItem> cartItems=cartItemDao.getCartItemsByCart(cart);
		
		for(CartItem cartItem:cartItems){
			
			cartItem.setOrderDetails(orderDetails);
			cartItem.setCartItemStatus("ORDERRED");
			cartItemDao.updateCartItem(cartItem);
		}
		
		System.out.println("cartItems are updated");
		
		cart.setCartTotal(0);
		cartDao.updateCart(cart);
		orderDetailsDao.updateOrderDetails(orderDetails);
		
		//for email
		String recipientAddress=user.getUserEmailId();
		String subject ="(NIIT ECOM) Order Confiremed";
		String message="Hello "+user.getUserName()+", \n"+"your order has been in recived on "+d.toString()+", and will be deliver to you soon. "
				+ "Total is INR"+orderDetails.getOrderDetailsTotal()+" only."
		+"\n Thank you.";
		System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
         
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
         
        // sends the e-mail
        mailSender.send(email);
		
		
		
		//end mail
		
		
		return modelAndView;
	
	}

	
	@RequestMapping("/orderDetailsView")
	ModelAndView orderDetailsView(Principal principal,HttpSession httpSession){
		ModelAndView modelAndView=new ModelAndView("orderDetailsView");
		List<OrderDetails> orderDetailsList=orderDetailsDao.getOrderDetailsListByUser(userDao.getUserByUserId(principal.getName()));
		if(orderDetailsList.isEmpty())
			System.out.println("**************************************order details list is empty");
		
		List<List<CartItem>> listOfCartItems=new ArrayList<List<CartItem>>();
		for(OrderDetails orderDetails:orderDetailsList){
			List<CartItem> cartItems=new ArrayList<CartItem>();
			cartItems.addAll(cartItemDao.getCartItemsByOrderdetails(orderDetails));
			listOfCartItems.add(cartItems);
		}
				
		
		
		
		httpSession.setAttribute("orderDetailsList",orderDetailsList);
		httpSession.setAttribute("listOfCartItems",listOfCartItems);
		return modelAndView;
	}
	
	
	
	

}
