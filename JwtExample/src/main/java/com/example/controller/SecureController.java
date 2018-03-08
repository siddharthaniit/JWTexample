package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.User;
import com.example.config.JwtFilter;
import com.example.service.UserService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/secure")
public class SecureController {

	@Autowired
	private UserService userService;

	

	@RequestMapping(value = "/user/email", method = RequestMethod.POST)
	public User findByEmail(HttpServletRequest request) throws JSONException {
		Claims claims = JwtFilter.claims;
		String string = claims.toString();
		String replaceAll = string.replaceAll("=", ":");
		JSONObject jsonObject = new JSONObject(replaceAll);
		String email = jsonObject.getString("sub");
		return userService.findByEmail(email);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		return userService.save(user);
	}
}
