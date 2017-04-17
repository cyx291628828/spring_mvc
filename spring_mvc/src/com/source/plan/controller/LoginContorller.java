package com.source.plan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginContorller {
	
	
	@RequestMapping(value="/MyLogin")
	public String myLogin(){
		return "login/MyLog";
	}
	
	@RequestMapping(value="/register")
	public String register(){
		return "login/register";
	}
	
	@RequestMapping(value="/sumbit")
	public String sumbit(){
		//判断登录的数据
		return "login/register";
	}
}
