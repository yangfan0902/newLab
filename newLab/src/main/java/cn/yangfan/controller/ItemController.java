package cn.yangfan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ItemController {
	
	@RequestMapping("/")
	public ModelAndView  index(){
		ModelAndView model=new ModelAndView("index");
		return model;
	}
}
