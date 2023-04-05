package com.examportal.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.helper.UserFoundException;
import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Set<UserRole> userRoles = new HashSet<>();
		
		user.setProfile("default.png");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

		Role role = new Role();
		role.setRoleId(5555L);
		role.setRolename("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		userRoles.add(userRole);
		System.out.println(user);
		return this.userService.createUser(user, userRoles);
	}

	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return this.userService.getUserByUsername(username);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		this.userService.deleteUserById(id);
	}

	@GetMapping("/alluser")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
		
	}
	@ExceptionHandler
	public ResponseEntity<?> exceptionHandler(UserFoundException exception)
	{
		return ResponseEntity.ok(exception.getMessage());
		
	}
}
