package com.ty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ty.entity.Student;
import com.ty.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository str;

	public List<Student> getAll() {
		//List<Student> all = str.findAll();
		return str.findAll();
		
	}

	public Student enroll(Student st) {
		return str.save(st);
		
	}

	public boolean getByEmail(Student student) {
	    Optional<Student> existingStudent = str.findByEmail(student.getEmail()); 
	    if(existingStudent.isPresent()){
	    	return true;
	    }
	    else {
	    	enroll(student);
	    	return false;
	    }
	     
	}

	public void delete(Integer sid) {
	 str.deleteById(sid);
	 
		
	}

	public Student getById(Integer sid) {
		return str.findById(sid).get();
		
	}

}
