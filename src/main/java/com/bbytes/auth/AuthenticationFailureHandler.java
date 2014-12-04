package com.bbytes.auth;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	private String loginFailureURL;
	
	
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		getRedirectStrategy().sendRedirect(request, response, getLoginFailureURL());
	}

	
	private String getLoginFailureURL() {
		return loginFailureURL;
	}
	
	public void setLoginFailureURL(String loginFailureURL) {
		this.loginFailureURL = loginFailureURL;
	}
}
