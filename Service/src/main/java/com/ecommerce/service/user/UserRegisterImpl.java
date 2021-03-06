package com.ecommerce.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.models.Account.User;
import com.ecommerce.repositories.user.UserRepository;

@Component
public class UserRegisterImpl implements UserRegisterService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void save(String username, String password) {
		
		User user = new User(username, passwordEncoder.encode(password));
		userRepository.save(user);

	}
	
	public boolean changePassword(String username, String oldPasswd, String passwd) {
		User user = userRepository.findByUsername(username);
		if(passwordEncoder.matches(oldPasswd, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(passwd));
			userRepository.save(user);
			return true;
		}
		return false;
	}
	
	public boolean changeUsername(String currUsername, String newUsername) {
		try {
			User user = userRepository.findByUsername(currUsername);
			user.setUsername(newUsername);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isUserPresent(String username) {
		if(userRepository.findByUsername(username) == null)	return false;
		return true;
	}

}
