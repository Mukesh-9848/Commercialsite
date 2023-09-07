package com.webapp.eshoponline.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.eshoponline.model.User;
import com.webapp.eshoponline.repository.UserRepository;
import com.webapp.eshoponline.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void userSignup(User user) {
		
		userRepo.save(user);
	}

	@Override
	public User userLogin(String userName, String password) {
	
		return userRepo.findByUserNameAndPassword(userName, password);
	}

	@Override
	public User doUserExists(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUserName(username);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

	@Override
	public void editUser(User user) {
		userRepo.save(user);
	}



	

}
