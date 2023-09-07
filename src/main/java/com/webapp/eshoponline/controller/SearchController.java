package com.webapp.eshoponline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.eshoponline.model.Product;
import com.webapp.eshoponline.service.IProductService;

@Controller
public class SearchController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/search")
	public String postSearch(@RequestParam String searchItem,Model model){
		Product product =productService.getByName(searchItem);
		
	if(product!=null) {		
		 if (product.getCategory() == 1) {
	            product.setImageName(("/laptops/"+product.getImageName()));
	           
	            
	        } else if (product.getCategory() == 2) {
	        	  product.setImageName(("/smartphones/"+product.getImageName()));
	           
	        } else if (product.getCategory() == 3) {
	        	  product.setImageName(("/cameras/"+product.getImageName()));
	          
	        } else if (product.getCategory() == 4) {
	        	  product.setImageName(("/accessories/"+product.getImageName()));
	           
	        } else {
	           
	        }
		
		model.addAttribute("products",product);
		return "SearchedProduct";
	}
	else {
	
	model.addAttribute("null","Oops,Nothing Found!!");
		return "SearchedProduct";
	}
	}

}
