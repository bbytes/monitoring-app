package com.bbytes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.bbytes.auth.LoginUserDetails;
import com.bbytes.dao.UserLoginDao;
import com.bbytes.entity.UserEntity;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	private final Logger log = LoggerFactory
			.getLogger(UserLoginServiceImpl.class);
	@Autowired
	UserLoginDao userRepository;

	public UserEntity getSessionUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		@SuppressWarnings("unused")
		String name = auth.getName();
		UserEntity user = null;
		if (auth != null && auth.isAuthenticated()
				&& auth.getPrincipal() instanceof LoginUserDetails) {
			LoginUserDetails userDetails = (LoginUserDetails) auth
					.getPrincipal();
			if (userDetails != null) {
				user = userDetails.getUser();
				try {
					user = userRepository.findByEmail(user.getEmail());
				} catch (Exception e) {
					log.error("Error while retrieving session user", e);
				}
				if (user != null) {
					log.debug("current session user " + user.getEmail());
					System.out.println(user.getFname());
				}
			}

		}
		return user;

	}

	public boolean isLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			return auth.isAuthenticated();
		} else {
			return false;
		}
	}
}
