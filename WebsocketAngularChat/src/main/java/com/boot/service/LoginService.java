package com.boot.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.boot.model.User;
import com.boot.repository.UserRepository;

@Service
public class LoginService {

	public User userLogin(String userName, String password, UserRepository userRepo, User user,
			UserService userService) {
		if (userName != null && !userName.isEmpty()) {
			user = userRepo.findExistingUser(userName);
			if (user == null) {
				user = new User();
				user.setUserName(userName);
				user.setPassWord(password);
				user.setUserLastLogin(new Date(0));
				user.setUserStatus("ACTIVE");
				userRepo.save(user);
			} else {
				userService.toggleUserStatus(userRepo, user.getUserId(), "ACTIVE");
			}
		}
		return user;
	}
}
