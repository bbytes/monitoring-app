package com.bbytes.service;

import com.bbytes.entity.UserEntity;

public interface UserLoginService {

	UserEntity getSessionUser();

	boolean isLoggedIn();

}
