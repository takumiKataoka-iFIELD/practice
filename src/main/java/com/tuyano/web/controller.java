package com.tuyano.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("logName")
public class controller {
	ljl
	
	@Autowired
	UserEntityRepository UserEntityRepository;
 
	//ユーザー登録画面へ遷移
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String go(Model model) {
		return "userAdd";
	}
	
	//ユーザー登録
	@RequestMapping(value="/userAdd", method = RequestMethod.POST)
	public String userAdd(@RequestParam("name")String name,
							@RequestParam("email")String email,
							@RequestParam("password")String password) {
		UserEntityRepository.save(new UserEntity(name, email, password));
		return "userAdd";
	}
	
	//ユーザー全件表示
	@RequestMapping(value="/userData", method = RequestMethod.GET)
	public String userData(Model model) {
		List<UserEntity> data = UserEntityRepository.findAll();
		model.addAttribute("data", data);
		return "userData";
	}
	
	//名前検索
	@RequestMapping(value="/Nsearch", method = RequestMethod.GET)
	public String userSearch(@RequestParam("name")String name, Model model) {
		List<UserEntity> data = UserEntityRepository.findByName(name);
		model.addAttribute("data", data);
		return "userData";
	}
	
	//メールアドレス検索
	@RequestMapping(value="/Msearch", method = RequestMethod.GET)
	public String emailSearch(@RequestParam("email")String email, Model model) {
		List<UserEntity> data = UserEntityRepository.findByEmail(email);
		model.addAttribute("data", data);
		return "userData";
	}
	
	//名前とメールアドレス部分一致検索
	@RequestMapping(value="/NMsearch", method = RequestMethod.GET)
	public String nmsearch(@RequestParam("name")String name,
							@RequestParam("email")String email,Model model) {
		List<UserEntity> data = UserEntityRepository.findByNameContainingAndEmailContaining(name, email);
		model.addAttribute("data", data);
		return "userData";
	}
	
	//ログインとセッション
	@RequestMapping(value="/loginnn", method = RequestMethod.POST)
	public String session(@RequestParam("name")String name,
							@RequestParam("password")String password,Model model) {
		UserEntity data = UserEntityRepository.findByNameAndPassword(name, password);
		List<UserEntity> data1 = UserEntityRepository.findAll();
		model.addAttribute("data", data1);
		String res = "こんにちは、" + name + "さん";
		if(data != null) {
		model.addAttribute("logName", res);
		}
		return "userData";
	}
	
	//ログアウトとセッション破棄
	@RequestMapping(value="/logout",params = "logName", method=RequestMethod.GET)
	public String sessionout(HttpSession session,SessionStatus sessionStatus, Model mdoel) {
		sessionStatus.setComplete();
		session.removeAttribute("logName");
		return "userData";
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/a", method = RequestMethod.GET)
	public String index(@ModelAttribute("message")String msg,Model model) {
		System.out.println("@ModelAttribute(\"message\"):" + msg);
		model.addAttribute("title", "Hello Page");
		return "hello";
	}
	
	@RequestMapping(value="/a", method = RequestMethod.POST)
	public String form(@ModelAttribute("message")String msg,
						@ModelAttribute("input1")String input1,Model model) {
		System.out.println("@ModelAttribute(\"message\")" + msg);
		String res = "最後の入力：" + input1;
		model.addAttribute("title", "Anser Page");
		model.addAttribute("message", res);
		return "hello";
	}

}











