package com.timeoffms.web.service;

import com.timeoffms.web.dao.UserRepository;
import com.timeoffms.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll(){
		return userRepository.findAll();
	}

	public void saveUser(User user){
		userRepository.save(user);
	}

	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	public void deleteByUsername(String username){
		userRepository.delete(userRepository.findByUsername(username));
	}
}
