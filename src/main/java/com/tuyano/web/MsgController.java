package com.tuyano.web;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MsgController {

	@Autowired
	MsgDataRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	MsgDataDaoImpl dao;

	@RequestMapping("/msg")
	public ModelAndView msg(ModelAndView mav) {
		mav.setViewName("showMsgData");
		mav.addObject("title","Sample");
		mav.addObject("msg","MsgDataのサンプルです。");
		MsgData msgdata = new MsgData();
		mav.addObject("formModel",msgdata);
		List<MsgData> list = dao.getAll();
		mav.addObject("data",list);
		return mav;
	}
}
