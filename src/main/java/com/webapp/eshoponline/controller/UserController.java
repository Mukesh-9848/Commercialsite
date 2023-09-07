package com.webapp.eshoponline.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.eshoponline.model.Product;
import com.webapp.eshoponline.model.User;
import com.webapp.eshoponline.repository.CartRepository;
import com.webapp.eshoponline.service.ICartService;
import com.webapp.eshoponline.service.IProductService;
import com.webapp.eshoponline.service.IUserService;
import com.webapp.eshoponline.utils.MailUtil;
import com.webapp.eshoponline.utils.VerifyRecaptcha;

@Controller
public class UserController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private MailUtil mailutil;
	
	@GetMapping("/")
	public String getIndex() {
		
		return "LoginForm";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		
		return "LoginForm";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		
		
		return "RegistrationForm";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute User user,Model model,@RequestParam String confirmpassword,@RequestParam(required = false) String termsandcondition) {
		
		if(user.getPassword().equals(confirmpassword)) {
			
			 if (termsandcondition != null && !termsandcondition.isEmpty()) {
		            userService.userSignup(user);
		            return "LoginForm";
		}
			else {
				model.addAttribute("tick","please tick checkbox to proceed");
				return "RegistrationForm";
			}
		}
		else {
			
			model.addAttribute("passmismatch","Password did not match!!");
		return "RegistrationForm";
		}
	}
	
	@PostMapping("/home")
	public String postLogin(@ModelAttribute User user,Model model,HttpSession session,@RequestParam("g-recaptcha-response") String gRecaptcha) throws IOException {
		
	
		
		if(VerifyRecaptcha.verify(gRecaptcha)) {
			
			User u = userService.userLogin(user.getUserName(), user.getPassword());
			if (u != null) {
				
				//model.addAttribute("user", userService.userLogin(user.getUserName(), user.getPassword()));
				session.setAttribute("validuser", u);
				 List<Product> productList =productService.getAllProducts();
			      for (Product products : productList) {
				        Product product = productService.getByName(products.getName());

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
				        
				      
				        products.setImageName("/"+category+"/"+products.getImageName());
				       
				 }
					model.addAttribute("product",productList);
	             session.setMaxInactiveInterval(0);
				return "Home";
			
		}
		else {
			model.addAttribute("message", "User not found!!");
			return "LoginForm";
	}
	}
	
		model.addAttribute("recaptcha","Invalid Captcha!!");
		return "LoginForm";
	
}

	
	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		cartService.deleteCart();
		session.invalidate();
		return "LoginForm";
	}
	
	@GetMapping("/home")
	public String getHome(HttpSession session,Model model) {
      if(session.getAttribute("validuser")==null) {
			return "LoginForm";
		}
      List<Product> productList =productService.getAllProducts();
      for (Product products : productList) {
	        Product product = productService.getByName(products.getName());

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
	        
	      
	        products.setImageName("/"+category+"/"+products.getImageName());
	       
	 }
		model.addAttribute("product",productList);
		//User u =userService.doUserExists();
		
		return "Home";
	}
	
	@GetMapping("/privacypolicy")
	public String getPrivacyPolicy() {
		
		return "PrivacyPolicy";
	}
	
	@GetMapping("/termsandconditions")
	public String getTermsAndConditions() {
		
		return "TermsandConditions";
	}
	
	@GetMapping("/myprofile")
	public String getMyProfile() {
		
		//model.addAttribute("ulist",userService.getUserById(id));
		
		return "MyProfile";
	}
	
	@PostMapping("/edit")
	public String postUpdateUser(@ModelAttribute User user) {
		userService.editUser(user);
		return "redirect:/edit";
	}
	
	@GetMapping("/forgotpassword")
	public String getForgotPassword() {
		
		return "ForgotPasswordForm";
	}
	
	@PostMapping("/reset")
	public String postResetPassword(@RequestParam String email) {
		
		mailutil.sendEmail(email);
		
		return "ResetMessage";
		
	}
	@GetMapping("/reset")
	public String postResetPassword() {
		
	
		
		return "ResetPasswordForm";
		
	}
	@PostMapping("/resetpassword")
	public String postResetpassword(@ModelAttribute User user,@RequestParam String confirm_password, Model model) {
User userr = userService.doUserExists(user.getUserName());
		
		if(userr !=null) {
		
			if(user.getPassword().equals(confirm_password)) {
				userr.setPassword(user.getPassword());
				
				userService.userSignup(userr);
				
				model.addAttribute("success","Password Changed Successfully!!!");
				return "LoginForm";
			}else {
				model.addAttribute("passwordmatching","Password did not match");
			}
		}else {
		model.addAttribute("usermessage","User doesnot exist!!");
		}
		
		return "ResetPasswordForm";
		
	}
}
