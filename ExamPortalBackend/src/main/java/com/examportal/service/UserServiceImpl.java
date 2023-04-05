package com.examportal.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.dao.RoleRepository;
import com.examportal.dao.UserRepository;
import com.examportal.helper.UserFoundException;
import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	

	 @Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		 User userByDatabase = this.userRepository.getUserByUsername(user.getUsername());
		if (userByDatabase != null) {
			System.out.println("User is already Present in the database");
			throw new UserFoundException();
		} else {
			// create the user
			for (UserRole userrole : userRoles) {
				Role role = userrole.getRole();
				roleRepository.save(role);
			}
			user.getUserRoles().addAll(userRoles);
			
		 return this.userRepository.save(user);
		}
		

	}
	 

	@Override
	public User getUserByUsername(String username) {
		return this.userRepository.getUserByUsername(username);
	}

	@Override
	public void deleteUserById(Long id) {

		userRepository.deleteById(id);
		;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	

}
