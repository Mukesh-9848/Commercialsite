package com.webapp.eshoponline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.eshoponline.model.Cart;
import com.webapp.eshoponline.model.Checkout;
import com.webapp.eshoponline.model.Product;
import com.webapp.eshoponline.service.ICartService;
import com.webapp.eshoponline.service.ICheckoutService;
import com.webapp.eshoponline.service.IProductService;

@Controller
public class CheckoutController {
	
	@Autowired
	private ICheckoutService checkoutService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/checkout")
	public String getCheckout(Model model) {
		List<Cart> products = cartService.getAll();
		double Total=0.0;
		
		 for (Cart cart : products) {
		        Product product = productService.getByName(cart.getName());
                double itemPrice = Double.parseDouble(product.getPrice());
		        Total += itemPrice;
		 }
		  model.addAttribute("totalprice",Total);		
			model.addAttribute("product",products);
		return "Checkout";
	}
	
	@PostMapping("/continuecheckout")
	public String postCheckout(@ModelAttribute Checkout checkout) {
		
		checkoutService.saveCheckoutInfo(checkout);
		cartService.deleteCart();
		
		return "CheckoutComplete";
	}

}
