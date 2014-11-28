package com.bbytes.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.bbytes.entity.UserEntity;
import com.bbytes.service.IAuthenticatorService;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
    
	private final Logger log = LoggerFactory.getLogger(AuthenticationProvider.class);

	@Autowired
	private IAuthenticatorService authService;


	protected final UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		String password = authentication.getCredentials().toString();
		UserEntity user = authService.authenticateUser(email, password);
		if (user.isActive()) {
			log.debug("auth successful...");
			return getUserDetails(user, password);
		} else {
			return new LoginUserDetails(null, null);
		}
	}
	
	private UserDetails getUserDetails(UserEntity user, String password) {
		if (user != null) {
			return new LoginUserDetails(user, password);
		}
		return null;
	}
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

}
