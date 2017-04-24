package com.source.plan.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.beans.Answer;
import com.source.plan.beans.PageInfo;
import com.source.plan.beans.Question;
import com.source.plan.jdbc.connectSQL;


@Controller
public class ScanQuestionController {
	
	@RequestMapping(value="/ScanQuestion")
	public String scanQuestion(@ModelAttribute("QuestionID") String QuestionID,HttpServletRequest request, HttpServletResponse response){
//		String QuestionID = request.getParameter("QuestionID");
		Question requestQuestion = new Question();
		Connection con = connectSQL.getConnection();
		PageInfo pageInfo = new PageInfo();
		String[] genres = new String[10];
		String genre = "";
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement("UPDATE question SET QuestionPageView = QuestionPageView +1 where QuestionID = ?");
			pstmt.setString(1, QuestionID);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("select * from question where QuestionID = ?");
			pstmt.setString(1, QuestionID);
			System.out.println(QuestionID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				requestQuestion.setQuestionID(rs.getString(1));
				requestQuestion.setQuestionTheme(rs.getString(2));
				System.out.println(rs.getString(2));
				requestQuestion.setQuestionContent(rs.getString(3).replaceAll("\n", "</p><p>"));
				requestQuestion.setQuestionUserID(rs.getString(4));
				requestQuestion.setQuestionStaTime(rs.getString(5));
				requestQuestion.setQuestionEndTime(rs.getString(6));
				requestQuestion.setQuestionFinTime(rs.getString(7));
				genres = rs.getString(8).split(","); 
				requestQuestion.setQuestionFile(rs.getString(9));
				requestQuestion.setQuestionMoney(rs.getString(10));
				requestQuestion.setQuestionisFinish(rs.getInt(11));
				requestQuestion.setQuestionisDownload(rs.getInt(12));
				requestQuestion.setQuestionDownMoney(rs.getString(13));
				requestQuestion.setQuestionPageView(rs.getInt(14));
			}
			pstmt = con.prepareStatement("select count(*) from answer where AnswerOfQuestion=?");
			pstmt.setString(1, requestQuestion.getQuestionID());
			rs = pstmt.executeQuery();
			if(rs.next()){
				requestQuestion.setQuestionAnwserQuantity(rs.getInt(1));
			}
			pstmt = con.prepareStatement("select UserName,UserLevel from user where UserID=?");
			pstmt.setString(1, requestQuestion.getQuestionUserID());
			ResultSet rs2 = pstmt.executeQuery();
			if(rs2.next())
			{
				requestQuestion.setQuestionUsername(rs2.getString(1));
				requestQuestion.setQuestionUserLevel(rs2.getInt(2));
			}
			for(int i = 0; i < genres.length;i++)
			{
				pstmt = con.prepareStatement("select GenreName from genre where GenreID=?");
				pstmt.setString(1, genres[i]);
				rs2 = pstmt.executeQuery();
				if(rs2.next())
					genre+=rs2.getString(1);
				if(i < genres.length-1)
					genre +=",";
			}
			requestQuestion.setQuestionGenre(genre);

			List<Answer> answers = new ArrayList<Answer>();
			pstmt = con.prepareStatement("select AnswerUserID,AnswerContent,AnswerTime,AnswerFile,AnswerIsSelect,AnswerID from answer where AnswerOfQuestion=?");
			pstmt.setString(1, QuestionID);
			ResultSet rs3 = pstmt.executeQuery();
			while(rs3.next()){
				Answer answer = new Answer();
				answer.setAnswerID(rs3.getString(6));
				answer.setAnswerUserID(rs3.getString(1));
				answer.setAnswerContent(rs3.getString(2));
				answer.setAnswerTime(rs3.getString(3));
				answer.setAnswerFile(rs3.getString(4));
				answer.setAnswerIsSelect(rs3.getString(5));
				pstmt = con.prepareStatement("select UserName,UserLevel,UserAnswerNum,UserAnswerScsNum from user where UserID=?");
				pstmt.setString(1, answer.getAnswerUserID());
				ResultSet rs4 = pstmt.executeQuery();
				if(rs4.next())
				{
					answer.setAnswerUsername(rs4.getString(1));
					answer.setAnswerUserLevel(rs4.getString(2));
					answer.setAnswerNum(rs4.getInt(3));
					answer.setAnswerSuccessNum(rs4.getInt(4));
				}
				answers.add(answer);
			}
			
			pageInfo.setAnswers(answers);
			pageInfo.setQuestion(requestQuestion);
		}catch (Exception e) {
			return "error.jsp";
		}finally{
			connectSQL.closePreparedStatement(pstmt);
			connectSQL.closeConnection(con);
		}
		request.setAttribute("request", pageInfo);
		return "question/ScanQuestion";
	}
	

}
