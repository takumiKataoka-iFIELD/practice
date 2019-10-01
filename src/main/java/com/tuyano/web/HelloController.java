package com.tuyano.web;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@Autowired
	MyDataRepository repository;

	@PostConstruct
	public void init() {
		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("syada@tuyano.com");
		d1.setMemo("this is my data!");
		repository.save(d1);

		MyData d2 = new MyData();
		d2.setName("hanako");
		d2.setAge(12);
		d2.setMail("teee@cdscsd.com");
		d2.setMemo("regregr");
		repository.save(d2);

		MyData d3 = new MyData();
		d3.setName("takumi");
		d3.setAge(24);
		d3.setMail("takumi@gdi.com");
		d3.setMemo("rgrgrgre");
		repository.save(d3);
	}
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

	@GetMapping("/book4")
	public String book4(@ModelAttribute("formModel")MyData mydata,Model model) {
		model.addAttribute("formModel",mydata);
		List <MyData> list = repository.findAll();
		model.addAttribute("datalist",list);
		return "book4";
	}

	@PostMapping("/book4")
	public String book4Form(@ModelAttribute("formModel")@Validated MyData mydata,BindingResult result,Model model) {
		if(!result.hasErrors()) {
			repository.saveAndFlush(mydata);
			return "redirect:book4";
		}else {
			List <MyData> list = repository.findAll();
			model.addAttribute("datalist",list);
			return "book4";
		}

	}
}
