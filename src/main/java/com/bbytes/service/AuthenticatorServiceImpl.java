package com.bbytes.service;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.bbytes.auth.LoginUserDetails;
import com.bbytes.dao.UserLoginDao;
import com.bbytes.entity.UserEntity;
import com.bbytes.service.IAuthenticatorService;

@Service
public class AuthenticatorServiceImpl implements IAuthenticatorService {

	private final Logger log = LoggerFactory
			.getLogger(AuthenticatorServiceImpl.class);

	@Autowired
	private UserLoginDao userRepository;

	public AuthenticatorServiceImpl() {

	}

	public UserEntity authenticateUser(String email, String password)
			throws UsernameNotFoundException, BadCredentialsException {
		log.info(String.format("Login attempt for user: [%s]", email));
		if (email == null || email.isEmpty())
			throw new AuthenticationServiceException("No username entered");

		if (password == null || password.isEmpty())
			throw new AuthenticationServiceException("No password entered");

		UserEntity user = userRepository.findByEmail(email);

		/*
		 * if(user == null){ user = userRepository.findByEmail(email); }
		 */

		if (user == null) {
			throw new UsernameNotFoundException(String.format(
					"User [%s] does not exist.",

					email));
		} else if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException(String.format(
					"Password for user [%s] does not match.", email));
		}
		return user;

	}

	public void grantAuthority(UserEntity user, String password,
			HttpSession httpSession) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();

		List<GrantedAuthority> authList = new

		ArrayList<GrantedAuthority>(2);

		UsernamePasswordAuthenticationToken usernamePasswordAuthToken =

		new UsernamePasswordAuthenticationToken(new LoginUserDetails(user,
				password),

		password, authList);
		usernamePasswordAuthToken.setDetails(new LoginUserDetails

		(user, password));
		user.setPassword(null);
		usernamePasswordAuthToken.eraseCredentials();

		context.setAuthentication(usernamePasswordAuthToken);
		httpSession.setAttribute

		(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());

	}

}
