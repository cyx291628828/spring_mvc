package com.source.plan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.entity.User;
import com.source.plan.entity.UserDAO;
/**
 * 验证用户ID是否已经注册
 * @author Administrator
 *
 */
@Controller
public class JudegUserIDContorller {
	@RequestMapping(value="/JudegUserID")
	public String judegUserID(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = null;
		String userID = request.getParameter("userID");
		UserDAO userDAO = new UserDAO();
		User findById = userDAO.findById(userID);
		out  = response.getWriter();
		if(findById != null){
			out.write("fail");
		}else {
			System.out.println("可以使用该ID");
		}
		return null;
	}
}
