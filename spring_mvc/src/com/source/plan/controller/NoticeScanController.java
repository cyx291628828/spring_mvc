package com.source.plan.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.source.plan.jdbc.connectSQL;


@Controller
public class NoticeScanController {

	@RequestMapping(value="/NoticeScan")
	public String noticeScan(HttpServletRequest request, HttpServletResponse response,RedirectAttributes att){
		String noticeID = request.getParameter("NoticeID");
		String QuestionID = request.getParameter("QuestionID");
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement("UPDATE notice set NoticeIsCheck = 1 where NoticeID=?");
			pstmt.setString(1, noticeID);
			int rs = pstmt.executeUpdate();
			if(rs>0){
				att.addFlashAttribute("QuestionID", QuestionID);
				return "redirect:/ScanQuestion";
			}else{
				return "error";
			}
		}catch (Exception e) {
			return "error";
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}
	
	@RequestMapping(value="/NoticeDelete")
	public String noticeDelete(HttpServletRequest request, HttpServletResponse response){
		String noticeID = request.getParameter("NoticeID");
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement("delete from notice where NoticeID=?");
			pstmt.setString(1, noticeID);
			int rs = pstmt.executeUpdate();
			if(rs>0)
				return "redirect:/user/UserMessageCenter";
		}catch (Exception e) {
			return "error.jsp";
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
		return "redirect:/user/UserMessageCenter";
	}
}
