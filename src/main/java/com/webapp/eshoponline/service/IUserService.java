package com.webapp.eshoponline.service;

import com.webapp.eshoponline.model.User;

public interface IUserService {
	
	void userSignup(User user);
	User userLogin(String userName, String password);
	User doUserExists(String username);
	User getUserById(Long id);
	void editUser(User user);

}
