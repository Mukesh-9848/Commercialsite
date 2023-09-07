package com.webapp.eshoponline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.eshoponline.model.ContactForm;
import com.webapp.eshoponline.utils.MailUtil;

@Controller
public class ContactUsController {
	
	@Autowired
	private MailUtil mail;

	@GetMapping("/contactus")
	public String getContactus() {
		
		return "Contactus";
	}
	
	@PostMapping("/contactus")
	public String postContactUs(@ModelAttribute ContactForm contactform) {
		mail.sendEmail("bhatm836@gmail.com", contactform.getEmail(),"Message From ElectroShop","<sender>:"+contactform.getEmail()+"\n" +contactform.getMessage() );
		return "Contactus";
	}
}
;