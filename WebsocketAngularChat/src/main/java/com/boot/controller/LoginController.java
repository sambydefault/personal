package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.User;
import com.boot.repository.UserRepository;
import com.boot.service.LoginService;
import com.boot.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	private User doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println("username" + username);
		user = loginService.userLogin(username, password, userRepository, user, userService);
		return user;
	}

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	private List<User> findUsers(@RequestParam("userId") String loggedInUserId) {
		return userService.fetchActiveUsers(userRepository, loggedInUserId);
	}

	@RequestMapping(value = "/fetchuser", method = RequestMethod.POST)
	private User findAUser(@RequestParam("username") String loggedInUserId) {
		return userService.findExistingUser(userRepository, loggedInUserId);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	private boolean logout(@RequestParam("loggedinuserid") String loggedInUserId) {
		return userService.toggleUserStatus(userRepository, Long.valueOf(loggedInUserId), "INACTIVE");
	}

}
