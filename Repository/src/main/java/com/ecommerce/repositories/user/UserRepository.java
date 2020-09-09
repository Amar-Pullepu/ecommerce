package com.ecommerce.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
