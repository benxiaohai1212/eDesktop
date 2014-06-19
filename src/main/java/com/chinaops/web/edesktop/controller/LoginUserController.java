package com.chinaops.web.edesktop.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaops.web.edesktop.entity.User;
import com.chinaops.web.edesktop.service.LoginUserService;

@Controller
public class LoginUserController{
	private final Log log = LogFactory.getLog(this.getClass());
	
	private LoginUserService pcUserservice;
	
	@Autowired
	public void setPcUserservice(LoginUserService pcUserservice) {
		this.pcUserservice = pcUserservice;
	}

	
	 @RequestMapping("/login.htm")  
	 public String loginPageShow(){
		 return "login/login";
	 }
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String username,@RequestParam String password,HttpServletRequest request,HttpServletResponse response) {
		String result = "0";
		HttpSession session = request.getSession();
		List<User> users = pcUserservice.getUserByUsername(username);
		if(users != null){//0代表密码错误，2代表没有该用户，1代表登陆成功。
			for(User user : users){
				if(user.getPassword().equals(password)){
					session.setAttribute("user", user);
					result="1";
				}
			}
		}else{
			result = "2";
		}
		return result;
	}

	
	
}
