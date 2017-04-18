package com.source.plan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.entity.Question;
import com.source.plan.entity.QuestionDAO;
import com.source.plan.entity.User;
import com.source.plan.entity.UserDAO;
import com.source.plan.jdbc.connectSQL;
/**
 * 验证用户ID是否已经注册
 * @author Administrator
 *
 */
@Controller
public class successnumContorller {
	@RequestMapping(value="/successnum")
	public void successNum(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		Question question = new Question();
		QuestionDAO questionDAO = new QuestionDAO();
		List<Question> findByQuestionDownMoney = questionDAO.findByQuestionDownMoney(-1);
		int num = findByQuestionDownMoney == null ? 0 : findByQuestionDownMoney.size();
		out.write(num+"个");
	}
}
