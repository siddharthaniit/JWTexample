package com.example.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	User save(User user);

	User findByEmail(String email);

}
