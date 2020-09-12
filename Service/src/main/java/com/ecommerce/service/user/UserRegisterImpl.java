package com.ecommerce.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.models.User;
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

}
