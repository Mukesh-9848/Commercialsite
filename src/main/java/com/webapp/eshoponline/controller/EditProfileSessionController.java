package com.webapp.eshoponline.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.webapp.eshoponline.model.User;

@Controller
@SessionAttributes("validuser")

public class EditProfileSessionController {

	
	@GetMapping("/edit")
	public String getEditProfileForm(Model model, @SessionAttribute("validuser") User user,HttpSession session) {
		if(session.getAttribute("validuser")==null) {
			return "LoginForm";
		}
		model.addAttribute("user",user);
		return "EditProfileForm";
	}
}
