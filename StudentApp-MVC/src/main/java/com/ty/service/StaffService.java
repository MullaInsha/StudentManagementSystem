package com.ty.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.entity.Staff;
import com.ty.repository.StaffRepository;

@Service
public class StaffService {
	@Autowired
	private StaffRepository sr;
	
	public Staff save(Staff staff) {
		
		return sr.save(staff);
	}

	public boolean getByEmail(Staff staff) {
		Optional<Staff> byEmail = sr.findByEmail(staff.getEmail());
		if(byEmail.isPresent()) {
			return false;
		}
		else {
			sr.save(staff);
			return true ;
		}
		
	}

	public boolean login(String email, String pass) {
		Optional<Staff> byEmail = sr.findByEmail(email);
		if(byEmail.isPresent()) {
			if(byEmail.get().getPassword().equals(pass)) {
				return true;
			}
			return false;
		}
		return false;
		
	}

}
