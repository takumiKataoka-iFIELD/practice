package com.tuyano.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller {
	
	@Autowired
	userEntity userEntity;
 
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String go() {
		return "";
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String userAdd(@RequestParam(user_name)String user_name,
							@RequestParam(email)String email,
							@RequestParam(password)String password,Model model) {
		userEntity.save(user_name, email, password);
		return "";
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String userData(Model model) {
		return "";
	}
	
}
