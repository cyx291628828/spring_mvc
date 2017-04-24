package com.source.plan.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.source.plan.entity.Discuss;
import com.source.plan.entity.DiscussDAO;
import com.source.plan.entity.UserDAO;


@Controller
public class AddReplyController {

	@RequestMapping(value="/AddReply")
	public String addReply(HttpServletRequest request, HttpServletResponse response , RedirectAttributes att){
		try {
			UserDAO userDAO = new UserDAO();
			DiscussDAO discussDAO = new DiscussDAO();
			request.setCharacterEncoding("UTF-8");
			String DiscussID = getDiscussID();
			String UserID = request.getParameter("UserID");
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String MessageTime = format.format(date);
			String Content = request.getParameter("Content");
			String IsTheme1 = request.getParameter("IsTheme");
			int IsTheme = IsTheme1 == null ? 0:Integer.parseInt(IsTheme1);
			String IsReply1 = request.getParameter("IsReply");
			int IsReply = Integer.parseInt(IsReply1);
			
			String ReplyDiscussID = request.getParameter("ReplyDiscussID");
			String theme = request.getParameter("theme");
			Discuss save_discussDiscuss = new Discuss();
			save_discussDiscuss.setDiscussId(DiscussID);
			save_discussDiscuss.setUser(userDAO.findById(UserID));
			save_discussDiscuss.setDiscussTime(MessageTime);
			save_discussDiscuss.setDiscussContent(Content);
			save_discussDiscuss.setIsTheme(IsTheme);
			save_discussDiscuss.setIsReply(IsReply);
			save_discussDiscuss.setDiscussByReplyDiscussId(discussDAO.findById(ReplyDiscussID));
			save_discussDiscuss.setDiscussByThemeDiscussId(discussDAO.findById(theme));
			save_discussDiscuss.setLastTime(MessageTime);
			discussDAO.save(save_discussDiscuss);
			att.addFlashAttribute("DiscussID",theme);
			return "redirect:/Reply"; // 重定向到另一个controller。
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	private String getDiscussID() {
		DiscussDAO discussDAO = new DiscussDAO();
		try{
			Query findQueryByHQL = discussDAO.findQueryByHQL("from Discuss order by discussId desc");
			findQueryByHQL.setMaxResults(1);
			List<Discuss> list = findQueryByHQL.list();
			String id = "";
			if(list != null){
				id = list.get(0).getDiscussId();
			}else {
				id = "0000000000";
			}
			return String.format("%10d", (Integer.parseInt(id)+1)).replace(" ", "0");
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
		return "error";
	}
}
