package com.bbytes.dao;



import com.bbytes.entity.UserEntity;


public interface UserLoginDao {
	public UserEntity findByEmail(String email);
	
	
}
