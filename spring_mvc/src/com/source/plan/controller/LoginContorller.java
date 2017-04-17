package com.source.plan.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plan.entity.User;
import com.source.plan.entity.UserDAO;
import com.source.plan.vcode.ValidateCode;


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
	
	@RequestMapping(value="/vcode")
	public String vcode(HttpServletRequest request , HttpServletResponse response){
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		HttpSession session = request.getSession();

		ValidateCode vCode = new ValidateCode(100, 30, 4, 50);
		session.setAttribute(ValidateCode.VCODE_SESSION_KEY, vCode.getCode());
		try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			System.out.println("验证码错误！");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/sumbit")
	public String sumbit(HttpServletRequest request, HttpServletResponse response){
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String random = (String) request.getSession().getAttribute(ValidateCode.VCODE_SESSION_KEY);
		if(vcode.toLowerCase().equals(random.toLowerCase()))
		{
			User loginUser = new User();
			loginUser.setUserId(userID);
			loginUser.setUserPass(password);
			UserDAO userDAO = new UserDAO();
			List<User> checkUsers = userDAO.findByExample(loginUser);
			if(checkUsers.size() > 0){
				checkUsers.get(0).setUserImage(checkUsers.get(0).getUserImage() == null ? "peopleHead2.png" : checkUsers.get(0).getUserImage());
				request.getSession().setAttribute("sessionUser", checkUsers.get(0));
				return "../index";
			}
		}
		return null;
	}
}
