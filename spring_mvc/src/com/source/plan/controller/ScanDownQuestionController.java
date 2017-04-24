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
import com.source.plan.beans.Download;
import com.source.plan.beans.PageInfo;
import com.source.plan.beans.Pinglun;
import com.source.plan.beans.Question;
import com.source.plan.jdbc.connectSQL;


@Controller
public class ScanDownQuestionController {

	@RequestMapping(value="/ScanDownQuestion")
	public String scanDownQuestion(@ModelAttribute("QuestionID") String QuestionID,HttpServletRequest request , HttpServletResponse response ){
		Question requestQuestion = new Question();
		Answer answer = new Answer();
		List<Pinglun> pingluns = new ArrayList<Pinglun>();
		Connection con = connectSQL.getConnection();
		PageInfo pageInfo = new PageInfo();
		String[] genres = new String[10];
		String genre = "";
		PreparedStatement pstmt = null;
		try{
			pstmt = con.prepareStatement("UPDATE question SET QuestionPageView = QuestionPageView +1 where QuestionID = ?");
			pstmt.setString(1, QuestionID);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement("select * from question , answer where QuestionID = ? AND AnswerIsSelect = '1' AND AnswerOfQuestion = QuestionID");
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
				answer.setAnswerID(rs.getString(15));
				answer.setAnswerUserID(rs.getString(16));
				answer.setAnswerContent(rs.getString(17).replaceAll("\n", "</p><p>"));
				answer.setAnswerTime(rs.getString(18));
				answer.setAnswerFile(rs.getString(19));
				answer.setAnswerOfQuestion(rs.getString(20));
				answer.setAnswerIsSelect(rs.getString(21));
			}
//			pstmt = con.prepareStatement("select count(*) from answer where AnswerOfQuestion=?");
//			pstmt.setString(1, requestQuestion.getQuestionID());
//			rs = pstmt.executeQuery();
//			if(rs.next()){
//				requestQuestion.setQuestionAnwserQuantity(rs.getInt(1));
//			}
			//获取提问人的信息
			pstmt = con.prepareStatement("select UserName,UserLevel,UserImage from user where UserID=?");
			pstmt.setString(1, requestQuestion.getQuestionUserID());
			ResultSet rs2 = pstmt.executeQuery();
			if(rs2.next())
			{
				requestQuestion.setQuestionUsername(rs2.getString(1));
				requestQuestion.setQuestionUserLevel(rs2.getInt(2));
				requestQuestion.setQuestionUserImage(rs2.getString(3));
			}
			//获取回答人的信息
			pstmt = con.prepareStatement("select UserName,UserLevel,UserImage from user where UserID=?");
			pstmt.setString(1, answer.getAnswerUserID());
			rs2 = pstmt.executeQuery();
			if(rs2.next())
			{
				answer.setAnswerUsername(rs2.getString(1));
				answer.setAnswerUserLevel(rs2.getString(2));
				answer.setAnswerUserImage(rs2.getString(3));
			}
			
			Download download = new Download();
			//从download表中查出该问题喜欢的人数questionLikeNum和不喜欢的人数questionDislikesNum
			pstmt = con.prepareStatement("select IsZan,count(*) from download where DownQuestionID =? GROUP BY IsZan");
			pstmt.setString(1, requestQuestion.getQuestionID());
			ResultSet rs3 = pstmt.executeQuery();
			while(rs3.next()){
				if(rs3.getInt(1)==1)//1代表喜欢的人数
					download.setQuestionLikeNum(rs3.getString(2));
				if(rs3.getInt(1)==2)//2代表不喜欢的人数
					download.setQuestionDislikesNum(rs3.getString(2));
			}
			//从download表中查出该问题被下载的次数questionDownNum
			pstmt = con.prepareStatement("select COUNT(DownUserID) from download where DownID in ( SELECT MAX(DownID) FROM download GROUP BY DownUserID) AND DownQuestionID = ?");
			pstmt.setString(1, requestQuestion.getQuestionID());
			ResultSet rs4 = pstmt.executeQuery();
			if(rs4.next()){
				download.setQuestionDownNum(rs4.getString(1));
			}
			//从download表中查出该问题评论的次数questionCommentNum
			pstmt = con.prepareStatement("select count(*) from download where DownQuestionID =? and IsPing ='1'");
			pstmt.setString(1, requestQuestion.getQuestionID());
			ResultSet rs5 = pstmt.executeQuery();
			if(rs5.next()){
				download.setQuestionCommentNum(rs5.getString(1));
			}
			pageInfo.setDownload(download);
			
			//获取评论List
			pstmt = con.prepareStatement("select DownID, DownUserID, UserName ,UserImage, DownTime, PingTime, PingConcent , IsPing from download , user where DownQuestionID =? and DownUserID = UserID");
			pstmt.setString(1, requestQuestion.getQuestionID());
			ResultSet rs6 = pstmt.executeQuery();
			while(rs6.next()){
				Pinglun pinglun = new Pinglun();
				pinglun.setDownID(rs6.getString(1));
				pinglun.setDownUserID(rs6.getString(2));
				pinglun.setDownUserName(rs6.getString(3));
				pinglun.setDownUserImage(rs6.getString(4));
				pinglun.setDownTime(rs6.getString(5));
				pinglun.setPingTime(rs6.getString(6));
				pinglun.setPingConcent(rs6.getString(7));
				pinglun.setIsPing(rs6.getInt(8));
				pingluns.add(pinglun);
			}
			pageInfo.setPinglins(pingluns);
			
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

			
			pageInfo.setAnswer(answer);
			pageInfo.setQuestion(requestQuestion);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "errer";
		}finally{
			connectSQL.closePreparedStatement(pstmt);
			connectSQL.closeConnection(con);
		}
		request.setAttribute("request", pageInfo);
		 return "download/ScanDownLoadQuestion";
	}
}
