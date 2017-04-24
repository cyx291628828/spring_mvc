package com.source.plan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.beans.Top;
import com.source.plan.jdbc.connectSQL;

@Controller
public class TopTenController {
	
	@RequestMapping(value="/TopTen")
	public void scanQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<Top> tops = new ArrayList<Top>();
		String data = "";
		int i = 0;
		Rank(request, response , tops);
    	PrintWriter out = response.getWriter();
		for(Top t : tops){
			i++;
			if(i<4){
				data += ("<tr> <td><span class='label label-success'>");
			}else{
				data += ("<tr> <td><span class='label label-info'>");
			}
			data += (i+"</span></td> <td class='text text-success'> <a href='ScanDownQuestion?QuestionID="+t.getQuestionID()+"'>");
			data += (t.getQuestionTheme()+"</a> </td> <td><span class='progress progress-striped' style='margin-bottom: 0px;'>");
			data += (t.getQuestionDownMoney()+"/"+t.getQuestionNum()+"</span></td>");
			if(i>9) break;
        }
		out.write(data);
	}

	private void Rank(HttpServletRequest request, HttpServletResponse response , List<Top> tops) {
		response.setCharacterEncoding("utf-8");
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;

		try{
			ResultSet rr = null;
			String rrNum =  "0";
			String s ="22";
			pstmt = con.prepareStatement("select QuestionID , QuestionTheme , QuestionDownMoney from question where QuestionDownMoney > -1");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				//该问题下载的次数
				Top top = new Top();
				pstmt = con.prepareStatement("select COUNT(DownUserID) from download where DownID in ( SELECT MAX(DownID) FROM download GROUP BY DownUserID) AND DownQuestionID = "+rs.getString(1));
				rr = pstmt.executeQuery();
				if(rr.next()){
					rrNum = rr.getString(1);
				}else{
					rrNum ="0";
				}
				top.setQuestionID(rs.getString(1));
				if(rs.getString(2).length()>11){
					s = rs.getString(2).substring(0, 12)+"...";
				}else{
					s = rs.getString(2);
				}
				top.setQuestionTheme(s);
				top.setQuestionDownMoney(rs.getString(3));
				top.setQuestionNum(rrNum);
				tops.add(top);
			}
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
		Comparator<Top> comparator = new Comparator<Top>(){
	        public int compare(Top s1, Top s2) {
	            // 先排年龄
	        	int i1 = Integer.parseInt(s1.getQuestionNum());
	        	int i2 = Integer.parseInt(s2.getQuestionNum());
	        	int j1 = Integer.parseInt(s1.getQuestionID());
	        	int j2 = Integer.parseInt(s2.getQuestionID());
	        	if (i1 != i2){
	        		return i2 - i1;
	        	}else{
	        		return j2 - j1;
	        	}
	                
	        }
	    };
	    Collections.sort(tops,comparator);
		
	}
	
}
