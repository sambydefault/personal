package com.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.model.User;
import com.boot.repository.UserRepository;

@Service
public class UserService {

	public List<User> fetchActiveUsers(UserRepository userRepository, String loggedInUserId) {
		User user = userRepository.findOne(Long.valueOf(loggedInUserId));
		List<User> activeUserList = userRepository.findActiveUsers("ACTIVE");
		if (activeUserList != null && !activeUserList.isEmpty())
			activeUserList.remove(user);
		return activeUserList;
	}

	public User fetchLoggedInUser(UserRepository userRepo, String username) {
		return userRepo.findExistingUser(username);
	}

	public boolean toggleUserStatus(UserRepository userRepository, long userId, String status) {
		User user = userRepository.findOne(Long.valueOf(userId));
		if (user != null) {
			switch (status) {
			case "INACTIVE":
				user.setUserStatus(status);
				userRepository.saveAndFlush(user);
				break;
			case "ACTIVE":
				user.setUserStatus(status);
				userRepository.saveAndFlush(user);
				break;
			}
			return true;
		}
		return false;
	}
	
	public User findExistingUser(UserRepository userRepository,String username){		
		return userRepository.findExistingUser(username);
	}

}
