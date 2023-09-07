package com.webapp.eshoponline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
	
	@GetMapping("/aboutus")
	public String getAboutUs() {
		
		return "Aboutus";
	}

}
