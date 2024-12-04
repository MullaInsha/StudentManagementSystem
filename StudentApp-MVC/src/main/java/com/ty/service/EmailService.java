package com.ty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ty.dto.Dto;

@Service
public class EmailService {
@Autowired
private JavaMailSender javaMailSender;


public void test(Dto d) {
	
	SimpleMailMessage m=new SimpleMailMessage();
	m.setTo(d.getTo());
	m.setSubject(d.getSubject());
	m.setText(d.getBody());
	
	javaMailSender.send(m);
}
}
