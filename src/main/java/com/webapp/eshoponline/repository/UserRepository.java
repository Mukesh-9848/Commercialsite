package com.webapp.eshoponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.eshoponline.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	User findByUserNameAndPassword(String username, String password);
	
	User findByUserName(String username);

}