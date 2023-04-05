package com.examportal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.examportal.config.JwtUtils;
import com.examportal.helper.UserNotFoundException;
import com.examportal.model.JwtRequest;
import com.examportal.model.JwtResponse;
import com.examportal.model.User;
import com.examportal.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin("*")
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	@Autowired
	private JwtUtils jwtUtils;

	
	//generate token
	@PostMapping("/generate-token")
	public  ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found ");
		}
		
		UserDetails loadUserByUsername = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
	
		String token = this.jwtUtils.generateToken(loadUserByUsername);
	return ResponseEntity.ok(new JwtResponse(token));
	
	
	}
	
	
	private void authenticate(String username, String password) throws Exception {

		try {
			
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User Disabled"+e.getMessage());
		}
		catch(BadCredentialsException exception)
		{
			throw new Exception("Invalid credentials"+ exception.getMessage());
		}
	}
	
	
	//return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return ((User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
	}
}
