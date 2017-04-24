package com.source.plan.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.jdbc.connectSQL;

@Controller
public class IndexNoticeController {
	
	@RequestMapping(value = "/indexNotice")
	public void IndexNotice(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		PrintWriter out = null;
		try{
			out = response.getWriter();
			pstmt = con.prepareStatement("select UserName,QuestionMoney,QuestionStaTime,QuestionID,QuestionTheme from question,`user` where UserID = QuestionUserID order by QuestionStaTime desc limit 0,20");
			ResultSet rs = pstmt.executeQuery();
			String time1 = "";
			String time2 = "";
			String data = "";
			while(rs.next())
			{
				time2 = rs.getString(3).split(" ")[0];
				if(!time1.equals(time2)){
					if(!time1.equals("")){
						data += ("</div></div>");
					}
					data += ("<div class='cd-timeline-block'> " +
							"<div class='cd-timeline-img cd-picture'> " +
							"<img src='img/cd-icon-location.svg' alt='Picture'> </div>" +
							"<div style='margin-left: 50px;'><span>" + time2 +  "</span>");
				}
				data += ("<br><a href='ScanQuestion?QuestionID="+rs.getString(4)+"'>用户<font color='#27AE60'>"+rs.getString(1)+"</font>正在请求帮助!</a>");
				data += ("<p>主题:“"+rs.getString(5)+"”</p>");
				data += ("<p>金额:<font color='#f0f'>"+rs.getString(2)+"</font><img style='width: 15px; height: 15px' src='img/money.ico'><i style='margin-left:50px'>" + rs.getString(3).split(" ")[1] + "</i></p><hr>");
				time1 = time2;
			}
			data += ("</div></div>");
			out.write(data);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}
}
