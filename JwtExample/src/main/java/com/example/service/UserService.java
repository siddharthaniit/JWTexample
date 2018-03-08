package com.example.service;

import com.example.User;

public interface UserService {
	
	User save(User user);

	User findByEmail(String email);
	

}
