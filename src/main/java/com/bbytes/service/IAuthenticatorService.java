package com.bbytes.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bbytes.entity.UserEntity;

public interface IAuthenticatorService {
	UserEntity authenticateUser(String email, String password)
			throws UsernameNotFoundException, BadCredentialsException;

	
	void grantAuthority(UserEntity user, String password, HttpSession httpSession)
			throws Exception;

}
