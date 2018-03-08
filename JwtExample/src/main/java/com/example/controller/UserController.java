package com.example.controller;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.User;
import com.example.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		return userService.save(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody User login) throws ServletException {

		String jwtToken = "";

		if (login.getEmail() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}
		String email=login.getEmail();
		String password = login.getPassword();
		
		User user = userService.findByEmail(email);
		
		if (user == null) {
			throw new ServletException("User email not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("Invalid login. Please check your  password.");
		}
	
         
		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;

	}
	
	/*@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String findByEmail(HttpServletRequest request){
		
		final String jwt1 = request.getHeader("authorization");
		
		String[] jwt2 = jwt1.split(" ");
		
		String jwt = jwt2[1];
		
		System.out.println(jwt);

		  String result = "";

		  String[] parts = jwt.split("[.]");
		  
		  try
		  {
			  String part = parts[1];
			 
		    int index = 0;
		    for(String part: parts)
		    {
		      if (index >= 2)
		        break;

		      index++;
			  
		      byte[] partAsBytes = part.getBytes("UTF-8");
		      String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), "UTF-8");
		      result = decodedPart;
		      
		      
		      System.out.println(result);
		    
		  }
		  catch(Exception e)
		  {
		    throw new RuntimeException("Couldnt decode jwt", e);
		  }
		  return result;

		}
		
		*/
	

}
