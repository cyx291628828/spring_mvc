package com.source.plan.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.jdbc.connectSQL;


@Controller
public class IsFinishController {
	
	@RequestMapping(value="/isFinish")
	public String isFinish(HttpServletRequest request, HttpServletResponse response){
		String QuestionID = (String)request.getParameter("QuestionID");
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = connectSQL.getConnection();
			pstmt = con.prepareStatement("select * from question where QuestionID = ? and QuestionIsFinish=1");
			pstmt.setString(1, QuestionID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return "redirect:/ScanDownQuestion";
			}else{
				return "redirect:/ScanQuestion";
			}
		} catch (Exception e) {
			return "error";
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}

}
