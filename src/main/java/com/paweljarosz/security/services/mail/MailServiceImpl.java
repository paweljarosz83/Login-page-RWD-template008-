package com.paweljarosz.security.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendPasswordRecoveryEmail(String url,String email) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(email);
		mail.setFrom("janek@fanek.com");
		mail.setSubject("Password recovery for test4test.io");
		
		mail.setText("Hi, \n"
				+ "here's the link to change your password: \n \n"
				+ url
				+ "\n \n"
				+ "You have 24 hours, after that the link will expire, and you will have to request it again.\n"
				+ "If you didn’t make this request, just ignore this message.\n \n"
				+ "Thanks for being with us. Hope you'll make awesome games! \n"
				+ "test4test.io team");
		mailSender.send(mail);
	}
}
