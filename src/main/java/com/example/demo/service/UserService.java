package com.example.demo.service;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.BankUsers;

@Service
public class UserService {
	
	private UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao=userDao;
	}
	
	
	public BankUsers saveUsers(BankUsers users) {
		users.setFirstPin(encode(users.getFirstPin()));
		return userDao.save(users);
	}
	
	 public String encode(String passWord) {
	        BCryptPasswordEncoder bCryptPasswordEncoder =
	                new BCryptPasswordEncoder(10, new SecureRandom());
	        return bCryptPasswordEncoder.encode(passWord);

	    }
	
}
