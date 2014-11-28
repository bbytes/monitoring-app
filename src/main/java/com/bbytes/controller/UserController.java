package com.bbytes.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.bbytes.entity.UserEntity;
import com.bbytes.service.UserRegisterService;


@Controller

public class UserController {
	 @Autowired
	    private UserRegisterService userService;

	   

	    @RequestMapping(value = "/addUser", method = RequestMethod.POST , headers={"content-type=application/json"})
	    public String addUser(@RequestBody/*(value="/user")*/ UserEntity user, BindingResult result) {
	    	
	    	userService.addUser(user);
	    	return "index";
	    	
	    	
	    }

	   
	    @RequestMapping("/signup")
	    public String getUserPartialPage(ModelMap modelMap) {
	        return "user/signup";
	    }
	    
	    @RequestMapping("/index")
	    public String getIndexPage(ModelMap modelMap) {
	        return "index";
	    }

}
