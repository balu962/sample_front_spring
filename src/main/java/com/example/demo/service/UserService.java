package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CUser;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public CUser checkAndCreateGuest (String ipAddress) {
		CUser curUser = this.userRepository.findByIpAddress(ipAddress);
		if (curUser!=null) {
			return curUser;
	        } else {
		        CUser newUser = new CUser();
		        newUser.setIpAddress(ipAddress);
		        newUser.setRole("Guest");
		        this.userRepository.save(newUser);
		        return newUser;
	        }
     };
     public CUser getAdminUser() {
    	 return this.userRepository.findByRole("admin");
     }
}
