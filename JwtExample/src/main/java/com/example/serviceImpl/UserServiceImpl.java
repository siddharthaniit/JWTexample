package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.User;
import com.example.dao.UserDao;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User save(User user) {
		
	 return	userDao.save(user);
	 
	}

	@Override
	public User findByEmail(String email) {
		
		return userDao.findByEmail(email);
	}
	

}
