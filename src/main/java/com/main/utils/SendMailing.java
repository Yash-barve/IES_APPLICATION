package com.main.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailing {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMailPwd(String subject, String body, String to) {

		boolean issent = false;

		try {
			MimeMessage mimeMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);

			javaMailSender.send(mimeMsg);
			issent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return issent;
	}


}
