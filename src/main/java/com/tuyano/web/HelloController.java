package com.tuyano.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/book2")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("book2");
		mav.addObject("msg","フォームを送信してください");
		return mav;
	}

	@PostMapping("/book2")
	public ModelAndView send(
			@RequestParam(value = "check1",required = false)boolean check1,
			@RequestParam(value = "radio1", required = false)String radio1,
			@RequestParam(value = "select1",required = false)String select1,
			@RequestParam(value = "select2",required = false)String[] select2,
			ModelAndView mav) {

		String res = "";
		try {
			res ="check:" + check1 +
					"radio:" + radio1 +
					"select:" + select1 +
					"select2:";
		}catch(NullPointerException e){}
		try {
			res += select2[0];
			for (int i = 1; i < select2.length; i++) {
				res += "," + select2[i];
			}
		}catch(NullPointerException e) {
			res += "null";
		}
		mav.addObject("msg",res);
		mav.setViewName("book2");
		return mav;
	}


	@RequestMapping("/{num}")
	public String index(@PathVariable int num ,Model model) {
		int res = 0;
		for(int i=1;i <= num;i++) {
			res += i;
		}
		model.addAttribute("msg","total: " + res);
		return "book";

	}
}
