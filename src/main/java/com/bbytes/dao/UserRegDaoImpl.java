package com.bbytes.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.bbytes.entity.UserEntity;

@Repository
public class UserRegDaoImpl implements UserRegisterDao{

	@Autowired
	 private SessionFactory sessionFactory;
	public void addUser(@RequestBody UserEntity user) {
		user.setActive(true);
		sessionFactory.getCurrentSession().save(user);
		System.out.println("Successfully saved in database");
		
	}
	
	
}
