package com.tuyano.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

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

	@PersistenceContext
	EntityManager entityManager;

	MyDataDaoImpl dao;
	@PostConstruct
	public void init() {
		dao = new MyDataDaoImpl(entityManager);
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
	@GetMapping("entitymanager")
	public ModelAndView entity(ModelAndView mav) {
		mav.setViewName("entitymanager");
		mav.addObject("msg","MyDataのサンプルです");
		List <MyData> list = dao.getAll();
		mav.addObject("datalist",list);
		return mav;
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
			repository.save(mydata);
			return "redirect:book4";
		}else {
			List <MyData> list = repository.findAll();
			model.addAttribute("datalist",list);
			return "book4";
		}

	}

	@RequestMapping("/find")
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyDataのサンプルです");
		mav.addObject("value","");
		List <MyData> list = dao.getAll();
		mav.addObject("data",list);

		return mav;
	}

	@PostMapping("/find")
	public ModelAndView search(HttpServletRequest request,ModeAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if (param == "") {
			mav = new ModelAndView("redirect:/find");
		}else {
			mav.addObject("title","Find result");
			mav.addObject("msg","「" + param + "」の検索結果");
			mav.addObject("value",param);
			List<MyData> list = dao.find(param);
			mav.addObject("data",list);
		}
		return mav;
	}
}
