package com.source.plan.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.entity.User;
import com.source.plan.entity.UserDAO;
import com.source.plan.vcode.ValidateCode;


@Controller
@RequestMapping("/login")
public class LoginContorller {
	
	/**
	 * 进入登录界面
	 * @return
	 */
	@RequestMapping(value="/MyLogin")
	public String myLogin(){
		return "login/MyLog";
	}
	/**
	 * 进入注册界面
	 * @return
	 */
	@RequestMapping(value="/register")
	public String register(){
		return "login/register";
	}
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value="/logoff")
	public String logoff(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.removeAttribute("sessionUser");
		return "index";
	}
	/**
	 * 确定注册
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/rsumbit")
	public String rsumbit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		User regUser = new User();
		UserDAO regUserDAO = new UserDAO();
		regUser.setUserId(request.getParameter("userID"));
		regUser.setUserName(request.getParameter("Username"));
		regUser.setUserPass(request.getParameter("password1"));
		regUser.setUserSex("男");
		regUser.setUserBirth("1990-01-01 00:00:00");
		regUser.setUserJob("未知");
		regUser.setUserFakeMoney(100);
		regUser.setUserInterest("这家伙很懒，什么也没留下");
		regUser.setUserLevel(0);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		regUser.setUserRegTime(time);
		regUserDAO.save(regUser);
		User findUser = regUserDAO.findById(regUser.getUserId());
		if(findUser != null){
			request.setAttribute("result", "reg");
			return "login/MyLog";
		}else {
			return "login/register";
		}
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
				return "index";
			}else {
				request.setAttribute("result", "password");
				return "login/MyLog";
			}
		}else {
			request.setAttribute("result", "vcode");
			return "login/MyLog";
		}
	}
}
