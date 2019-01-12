package cn.yangfan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yangfan.service.UserService;
import pojo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/student/login")
	public ModelAndView studentLogin(User user) {
		ModelAndView model = new ModelAndView("img2word");

		System.out.print(user.getUsername());
		return model;
	}

	@RequestMapping("/manager/login")
	public ModelAndView managerLogin(User user) {
		ModelAndView model = new ModelAndView("img2word");

		System.out.print(user.getUsername());
		return model;
	}
	@RequestMapping("/goToRegister")
	public String goToRegister() {
		
		return "register";
	}
	@RequestMapping("/register")
	public String register(User user) {
		try{
			userService.addUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.print(user.getUsername());
		System.out.print(user.getPassword());
		return "redirect:/registerBack.html";
	}
	
	@RequestMapping("/register/confirm")
	@ResponseBody
	public Map register(String username,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map result=new HashMap();
		System.out.print(username);
		User user=userService.getUserByName(username);
		if(user==null){
			result.put("state", 200);
		}else{
			result.put("state", 400);
		}
		
		return result;
	}

}
