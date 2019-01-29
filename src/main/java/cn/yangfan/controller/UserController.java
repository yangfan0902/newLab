package cn.yangfan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import cn.yangfan.service.UserService;
import pojo.Result;
import pojo.User;
import pojo.UserResult;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/student/login")
	public String studentLogin(User user,HttpSession session) {
		
		String name=user.getName();
		session.removeAttribute("username");
		session.setAttribute("username", name);
		return "redirect:/itemList.html";
	}

	@RequestMapping("/manager/login")
	public String managerLogin(User user) {
		System.out.print(user.getName());
		String name=user.getName();
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
		System.out.print(user.getName());
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
			
		User user=userService.getUserByName(checkUser.getName());
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
	@ResponseBody
	public Map logout(HttpSession session){

		session.removeAttribute("username");;
	
		Map result=new HashMap();
		result.put("state", "200");
		result.put("msg","退出成功");
		return result;
		
		
	}
	
	@RequestMapping("/index")
	@ResponseBody
	public Result userList(){
		List users=userService.getUsers();
		Result result=new Result();
		result.setStatus("0");
		result.setList(users);
		
		return result;
		
		
	}
	
	//修改密码检验
	@RequestMapping("/password")
	@ResponseBody
	public Map register(User user,HttpServletRequest request) {
		String type=request.getParameter("type");
		Map result=new HashMap();
		if("check".equals(type)){
			User myuser=userService.getUserByName(user.getName());
			if(myuser==null){
				result.put("state", 400);
				result.put("msg","与原密码不一致，请输入正确的原密码");
			}else if(!(myuser.getPassword().equals(user.getPassword()))){
				result.put("state", 400);
				result.put("msg","与原密码不一致，请输入正确的原密码");
			}
			
			return result;
		}
		else{
			User myuser=userService.getUserByName(user.getName());
			myuser.setPassword(user.getPassword());
			userService.updateUser(myuser);
			result.put("state", 200);
			result.put("msg","密码修改成功");
			return result;
		}

		
	}

}
