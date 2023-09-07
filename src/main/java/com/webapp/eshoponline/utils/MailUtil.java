package com.webapp.eshoponline.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String to,String from,String subject,String message) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(to);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setText(message);
		
		mailSender.send(msg);
	}
public void sendEmail(String to) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setTo(to);
		

        msg.setSubject("Reset Password");
        
        //generates random password 
        //msg.setText("Click on the link to reset password :"+UUID.randomUUID().toString().substring(0, 8));
        
        String reset = "http://localhost/reset";
        
        msg.setText("Click on the link to reset password :"+ reset);
		
		mailSender.send(msg);
	}
}
