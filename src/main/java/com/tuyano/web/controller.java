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
	UserEntityRepository userEntity;
 
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String go() {
		return "userData";
	}
	
	@RequestMapping(value="/userAdd", method = RequestMethod.POST)
	public String userAdd(@RequestParam(name)String name,
							@RequestParam(email)String email,
							@RequestParam(password)String password) {
		userEntity.save(name, email, password);
		return "userData";
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String userData(Model model) {
		return "";
	}
	
}
