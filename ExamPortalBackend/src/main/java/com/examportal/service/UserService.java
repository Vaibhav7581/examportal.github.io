package com.examportal.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;

import com.examportal.model.User;
import com.examportal.model.UserRole;

public interface UserService {

	// Create an User

	public User createUser(User user, Set<UserRole> userRoles) throws Exception ;

	public User getUserByUsername(String username);

	public void deleteUserById(Long id);

	public List<User> getAllUsers();
}
