package com.bbytes.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private String loginSuccessURL;

	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		getRedirectStrategy().sendRedirect(request, response, getLoginSuccessURL());
	}

	
	private String getLoginSuccessURL() {
		return loginSuccessURL;
	}

	
	public void setLoginSuccessURL(String loginSuccessURL) {
		this.loginSuccessURL = loginSuccessURL;
	}

}
