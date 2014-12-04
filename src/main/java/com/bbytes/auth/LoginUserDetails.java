package com.bbytes.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bbytes.entity.UserEntity;

public class LoginUserDetails implements UserDetails{
	
	
  
	private static final long serialVersionUID = 1L;

	private String email;
	
	private String password;
	
	private UserEntity user;
	
	public LoginUserDetails(UserEntity user, String password) {
		this.user = user;
		if(user!=null){
			this.email = user.getEmail();
			this.password = password;
		}
	}
	
	public UserEntity getUser(){
		return this.user;
	}

	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	
	public String getPassword() {
		return password;
	}

	
	public String getUsername() {
		return email;
	}

	
	public boolean isAccountNonExpired() {
		
		return true;
	}

	
	public boolean isAccountNonLocked() {
	
		return true;
	}

	
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	
	public boolean isEnabled() {
		
		return true;
	}

}
