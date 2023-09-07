package com.webapp.eshoponline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.webapp.eshoponline.model.Cart;
import com.webapp.eshoponline.model.Product;
import com.webapp.eshoponline.service.ICartService;
import com.webapp.eshoponline.service.IProductService;

@Controller
public class CartController {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IProductService productService;

	//private Cart[] products;

	
	@GetMapping("/viewcart")
	public String getViewCart(Model model) {
		List<Cart> products = cartService.getAll();
		double Total=0.0;
		
		 for (Cart cart : products) {
		        Product product = productService.getByName(cart.getName());

		        String category;
		        if (product.getCategory() == 1) {
		            category = "laptops";
		           
		            
		        } else if (product.getCategory() == 2) {
		            category = "smartphones";
		           
		        } else if (product.getCategory() == 3) {
		            category = "cameras";
		          
		        } else if (product.getCategory() == 4) {
		            category = "accessories";
		           
		        } else {
		            category = "unknown";
		        }
		        
		      
		        cart.setImageName("/"+category+"/"+product.getImageName());
		        double itemPrice = Double.parseDouble(product.getPrice());
		        Total += itemPrice;
		 }
		        
        model.addAttribute("totalprice",Total);		
		model.addAttribute("product",products);
		return "View_Cart";
	}
	
	@GetMapping("/addtocart/{name}")
	public String postAddToCart(@PathVariable("name") String name,Model model,HttpServletRequest request) {
		
	Product product = productService.getByName(name);
	
	Cart cart = new Cart();
	cart.setName(product.getName());
	cart.setPrice(product.getPrice());
	cart.setImageName(product.getImageName());
	cart.setDescription(product.getDescription());
	 cartService.addCart(cart);
	 
	 String referer = request.getHeader("referer");
	 if (referer != null && !referer.isEmpty()) {
	        return "redirect:" + referer;
	    } else {
	        return "redirect:/"; // Fallback to a default page if referer is empty or null
	    }
	}
	
	@GetMapping("/removefromcart/{id}")
	public String getRemoveFromCart(@PathVariable("id") Long id, HttpServletRequest request) {
		cartService.deleteItemFromCart(id);
		
		 String referer = request.getHeader("referer");
		 if (referer != null && !referer.isEmpty()) {
		        return "redirect:" + referer;
		    } else {
		        return "redirect:/"; // Fallback to a default page if referer is empty or null
		    }
		
	}
}
