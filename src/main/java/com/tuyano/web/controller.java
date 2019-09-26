package com.tuyano.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller {
	
	@Autowired
	UserEntityRepository UserEntityRepository;
 
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String go() {
		return "userAdd";
	}
	
	@RequestMapping(value="/userA", method = RequestMethod.POST)
	public String userAdd(@RequestParam("name")String name,
							@RequestParam("email")String email,
							@RequestParam("password")String password) {
		UserEntityRepository.save(new UserEntity(name, email, password));
		return "userAdd";
	}
	
	@RequestMapping(value="/userData", method = RequestMethod.GET)
	public String userData(Model model) {
		List<UserEntity> data = UserEntityRepository.findAll();
		model.addAttribute("data", data);
		return "userData";
	}
	
}
