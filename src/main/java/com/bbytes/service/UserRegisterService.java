package com.bbytes.service;


import org.springframework.stereotype.Service;

import com.bbytes.entity.UserEntity;

@Service
public interface UserRegisterService {
	
	public void addUser(UserEntity user);
	
}
