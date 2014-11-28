package com.bbytes.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbytes.entity.UserEntity;
import com.bbytes.service.UserLoginService;

@Controller
@RequestMapping(value = "/login")
public class UserLogController {

	@Autowired
	private UserLoginService userLoginService;

	@RequestMapping(method=RequestMethod.GET)
	public String getUserPartialPage(ModelMap modelMap) {
		
		return "user/login";
		
		
		
	}

	@RequestMapping("/isLoggedIn")
	public @ResponseBody boolean isLoggedIn() {
		return userLoginService.isLoggedIn();
	}

	@RequestMapping("/loggedInUser")
	public @ResponseBody UserEntity getLoggedInUser() {
		UserEntity user = userLoginService.getSessionUser();
		return user;
	}

	@RequestMapping("/failure")
	public @ResponseBody String OnLoginFailure() {
		return "failure";
	}

	@RequestMapping("/success")
	public @ResponseBody UserEntity OnLoginSuccess() {
		UserEntity user = userLoginService.getSessionUser();
		return user;
	}

	@RequestMapping("/logout/success")
	public @ResponseBody String OnLogoutSuccess() {
		return "success";
	}

	@RequestMapping("/home")
	public  String AfterLoginSuccess() {
		
		
		return "user/home";
	}

}
