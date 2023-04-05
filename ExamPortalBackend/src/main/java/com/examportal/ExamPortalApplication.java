package com.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examportal.helper.UserFoundException;
import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.service.UserService;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		  try {
			System.out.println("starting code ......");
			 User user = new User();
			user.setUsername("Shrinivas9075");
			user.setFirstname("Shrinivas");
			user.setLastname("Somvanshi");
			user.setPassword(this.bCryptPasswordEncoder.encode("9075"));
			user.setPhonenumber("9822767959");
			user.setEmail("mamatasomvanshi108@gmail.com");
			user.setProfile("default.png");

			Role role = new Role();
			role.setRoleId(8888L);
			role.setRolename("ADMIN");

			Set<UserRole> userRoles = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);

			userRoles.add(userRole);
			User user2 = this.userService.createUser(user, userRoles);
			System.out.println(user2.getUsername());
		}
		catch (UserFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		 
		 
	}

}
