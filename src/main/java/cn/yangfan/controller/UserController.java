package cn.yangfan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import cn.yangfan.service.UserService;
import pojo.User;
import pojo.UserResult;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/student/login")
	public String studentLogin(User user,HttpSession session) {
		
		String name=user.getUsername();
		session.setAttribute("username", name);
		return "redirect:/itemList.html";
	}

	@RequestMapping("/manager/login")
	public String managerLogin(User user) {
		System.out.print(user.getUsername());
		String name=user.getUsername();
		return "redirect:/itemList.html?username="+name;
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
	
	@RequestMapping("/name")
	@ResponseBody
	public Map getUsername(HttpSession session) {
		String username=(String) session.getAttribute("username");
		Map result=new HashMap();
		result.put("username", username);
		return result;
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
	
	
	@RequestMapping("/loginCheck")
	@ResponseBody
	public UserResult getUserByName(@RequestBody User checkUser, HttpServletRequest request) {
		UserResult result=new UserResult();
			
		User user=userService.getUserByName(checkUser.getUsername());
		if(user==null){
			result.setState(1);
			result.setMsg("用户名不存在");
			return result;
		}
		else if(!user.getPassword().equals(checkUser.getPassword())){
			result.setState(2);
			result.setMsg("密码错误");
			return result;
		}
		
		if("admin".equals(request.getParameter("type"))){
			String role=user.getRole();
			if(!"admin".equals(role)){
				result.setState(3);
				result.setMsg("没有权限");
				
			}
		}
		
		return result;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(){
		
		ModelAndView model=new ModelAndView("index");
		return model;
	}
	

}
