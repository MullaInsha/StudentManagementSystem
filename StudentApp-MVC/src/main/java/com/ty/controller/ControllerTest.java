package com.ty.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ty.dto.Dto;
import com.ty.entity.Staff;
import com.ty.entity.Student;
import com.ty.service.EmailService;
import com.ty.service.StaffService;
import com.ty.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ControllerTest {
	@Autowired
	private StaffService staffService;	
	
	@Autowired
	private StudentService sts;
	
	
	@Autowired
	private EmailService es;
	
	
	
	@GetMapping("/")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("staff", new Staff());
		return mv;
	}	
	@PostMapping("/register")
	public ModelAndView postRegister(Staff staff) {
		boolean r=staffService.getByEmail(staff);
		ModelAndView m=new ModelAndView();
		if(r) {
			m.setViewName("register");
			m.addObject("msg","Registetred Successfully");
			
		}
		else {
			m.setViewName("register");
			m.addObject("msg","Already Exist");

		}
		return m;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	@PostMapping("/login")
	public ModelAndView postLogin(HttpServletRequest r) {
		String email=r.getParameter("email");
		String pass=r.getParameter("password");		
		boolean login = staffService.login(email,pass);
		
		ModelAndView m=new ModelAndView();
		if(login) {
			List<Student> all = sts.getAll();
			m.setViewName("home");
			m.addObject("sall",all);
			m.addObject("msg","Student Details");
			
		}
		else {
			m.setViewName("login");
			m.addObject("msg","Credential does not match");

		}
		return m;
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		List<Student> all = sts.getAll();
		ModelAndView mv = new ModelAndView("home");
		System.out.println("students"+all);
		mv.addObject("sall",all);
		return mv;
	}
	
	
	@GetMapping("/add")
	public ModelAndView Add() {
		ModelAndView mv = new ModelAndView("student");
		mv.addObject("s",new Student());
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView saveStudent(Student student) {
	    
	    boolean studentExists = sts.getByEmail(student);  

	    ModelAndView mv = new ModelAndView();
	   
	    if (studentExists) {
	    	 mv.setViewName("home"); 
	        mv.addObject("msg", "Student already exists");
	    } else {
	       
	            mv.setViewName("home");
	            mv.addObject("msg", "Student added successfully");
	       
	    }	   
	    List<Student> all = sts.getAll();  
	    mv.addObject("sall", all); 

	    return mv;
	}
	
	@GetMapping("/email")
	public ModelAndView Email() {
		
		ModelAndView mv=new ModelAndView("email");
		mv.addObject("dto",new Dto());
		
		return mv;
		
		
	}
	
	@PostMapping("/send")
	public ModelAndView sendEmail(Dto d ) {
		es.test(d);		
		ModelAndView mv = new ModelAndView("home");
		List<Student> all = sts.getAll();
		mv.addObject("sall", all);
		mv.addObject("msg", "Mail sent");
		return mv;
		
		
		
		
	}
	
	@GetMapping("/update")
	public ModelAndView update(@RequestParam Integer sid) {
		Student student = sts.getById(sid);
		ModelAndView mv=new ModelAndView("update");
		mv.addObject("stu", student);
		return mv;
		
		
	}
	
	
	@PostMapping("/update")
	public ModelAndView updateStudent(@ModelAttribute("student") Student student) {
		sts.enroll(student);
		ModelAndView mv = new ModelAndView("home");
		List<Student> all = sts.getAll();
		mv.addObject("sall", all);
		mv.addObject("msg", "Updated");
		return mv;
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Integer sid) {
		sts.delete(sid);
		List<Student> all = sts.getAll();
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("sall", all);
		mv.addObject("msg", "Deleted");
		return mv;
		
	}
  


	

}
