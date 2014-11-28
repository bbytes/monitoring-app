package com.bbytes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bbytes.service.UserRegisterService;
import com.bbytes.dao.UserRegisterDao;
import com.bbytes.entity.UserEntity;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	private UserRegisterDao UserRegisterDao;

	@Transactional
	public void addUser(UserEntity user) {
		System.out.println("hi");
		UserRegisterDao.addUser(user);

	}
}
