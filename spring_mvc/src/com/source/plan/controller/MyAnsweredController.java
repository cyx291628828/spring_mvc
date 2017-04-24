package com.source.plan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.beans.Genre;
import com.source.plan.beans.PageInfo;
import com.source.plan.beans.Question;
import com.source.plan.entity.User;
import com.source.plan.jdbc.connectSQL;


@Controller
public class MyAnsweredController {

	
	private final int GENRE_FRONT = 2; 
	private final int GENRE_BACKEND = 1; 
	private final int GENRE_MOBILE = 3; 
	private final int GENRE_OTHER = 4;
	private PageInfo pageInfo = null;
	String[] genres = new String[10];
	int pagenum = 0;
	List<Question> questions = null;
	private String genre = "";
	
	@RequestMapping(value="/MyAnswered")
	public String myAnswered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		pageInfo = new PageInfo();
		genres = new String[10];
		pagenum = 0;
		questions = new ArrayList<Question>();
		genre = "";
		Connection con = connectSQL.getConnection();
		User user = (User)request.getSession().getAttribute("sessionUser");
		if(user == null)
		{
			return "login/noLog";
		}
		
		String genreID = request.getParameter("genreID");
		String genreType = request.getParameter("genreType")==null?"0":request.getParameter("genreType");
		String sortRule = request.getParameter("sortRule")==null?"0":request.getParameter("sortRule");
		
		sortRule = getSortRule(Integer.parseInt(sortRule));
		List<Genre> genres = new ArrayList<Genre>();
		pageInfo = new PageInfo();
		questions = new ArrayList<Question>();
		String type = "%";
		String sql="select * from genre ";
		PreparedStatement pstmt = null;	
		pagenum = request.getParameter("pageNum")==null?1:Integer.parseInt(request.getParameter("pageNum"));
		
		try{
			if(genreID != null && !genreID.equals("0")){
				pstmt = con.prepareStatement("select GenreType from genre where GenreID=?");
				pstmt.setString(1, genreID);
				ResultSet rSet = pstmt.executeQuery();
				if(rSet.next())
					genreType = rSet.getString(1);
				genreType = ""+getGenreType(genreType);
			}
			sql += "where GenreType like ?";
			switch (Integer.parseInt(genreType)) {
			case GENRE_FRONT:
				type="前端开发";
				break;
			case GENRE_BACKEND:
				type="后端开发";
				break;
			case GENRE_MOBILE:
				type="移动开发";
				break;
			case GENRE_OTHER:
				type="其他";
				break;
			default:
				break;
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()){
				Genre g = new Genre();
				g.setGenreID(rs1.getString(1));
				g.setGenreName(rs1.getString(2));
				g.setGenreType(rs1.getString(3));
				genres.add(g);
				pageInfo.setGenres(genres);
			}
			if(genreID == null|| genreID.equals("0")){
				//如果语言名称为空 那么自动设置为全部
				TypeRequire(type, sortRule, user.getUserId());	
			}else {
				//以语言名称为搜索条件
				NameRequire(genreID, sortRule, user.getUserId());
			}
			pageInfo.setPageNum(pagenum);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("genreType", genreType);
			request.setAttribute("genreID", genreID);
			return "question/MyAnswered";
		}catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return "error";
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}
	
	
	
	int getGenreType(String s){
		int type = 0;
		if(s.equals("前端开发")){
			type = 2;
		}else if(s.equals("后端开发")){
			type = 1;
		}else if(s.equals("移动开发")){
			type = 3;
		}else if(s.equals("其他")){
			type = 4;
		}

		return type;
	}
	
	private String getSortRule(int sortRule){
		String sql = " ";
		switch(sortRule){
		case 1:sql = " order by QuestionEndTime ";break;
		case 2:sql = " order by QuestionEndTime desc ";break;
		case 3:sql = " order by QuestionPageView ";break;
		case 4:sql = " order by QuestionPageView desc ";break;
		case 5:sql = " order by QuestionMoney ";break;
		case 6:sql = " order by QuestionMoney desc ";break;
		}
		return sql;
		
	}
	
	void TypeRequire(String genreType, String sortRule, String UserID){
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		String sql = "where ( ";
		try {
			//获得符合要求的所有问题的数量
			pstmt = con.prepareStatement("select GenreID from genre where GenreType like ?");
			pstmt.setString(1, genreType);
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()){
				if(i == 0){
					sql += " QuestionGenre like ('%" + rs.getString(1) + "%') ";
				}
				else {
					sql += " or QuestionGenre like ('%" + rs.getString(1) + "%') ";
				}
				i++;
			}
			sql += ")";
			pstmt = con.prepareStatement("select QuestionID,QuestionTheme,QuestionUserID,QuestionStaTime,QuestionEndTime,QuestionMoney,QuestionPageView,QuestionGenre  " +
					"from question "+sql + " and QuestionID in (select AnswerOfQuestion from answer where AnswerUserID=?)");
			pstmt.setString(1, UserID);
			rs = pstmt.executeQuery();
			rs.last();
			pageInfo.setPageSum(rs.getRow()%9==0?rs.getRow()/9:rs.getRow()/9+1);
			
			
			pstmt = con.prepareStatement("select QuestionID,QuestionTheme,QuestionUserID,QuestionGenre,QuestionEndTime,QuestionMoney,QuestionPageView  " +
					"from question "+sql+" and QuestionID in (select AnswerOfQuestion from answer where AnswerUserID=?)" + sortRule +" limit ?,9");
			pstmt.setInt(2, (pagenum-1)*9);
			pstmt.setString(1, UserID);
			rs  = pstmt.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.setQuestionID(rs.getString(1));
				question.setQuestionTheme(rs.getString(2));
				question.setQuestionUserID(rs.getString(3));
				genres = rs.getString(4).split(",");
				question.setQuestionEndTime(rs.getString(5));
				question.setQuestionMoney(rs.getString(6));
				question.setQuestionPageView(rs.getInt(7));
				pstmt = con.prepareStatement("select UserName from user where UserID=?");
				pstmt.setString(1, question.getQuestionUserID());
				ResultSet rs2 = pstmt.executeQuery();
				if(rs2.next())
					question.setQuestionUsername(rs2.getString(1));
				pstmt = con.prepareStatement("select count(*) from answer where AnswerOfQuestion=?");
				pstmt.setString(1, question.getQuestionID());
				rs2 = pstmt.executeQuery();
				if(rs2.next()){
					question.setQuestionAnwserQuantity(rs2.getInt(1));
				}
				for(i = 0; i < genres.length;i++)
				{
					pstmt = con.prepareStatement("select GenreName from genre where GenreID=?");
					pstmt.setString(1, genres[i]);
					rs2 = pstmt.executeQuery();
					if(rs2.next())
						genre+=rs2.getString(1);
					if(i < genres.length-1)
						genre +=",";
				}
				question.setQuestionGenre(genre);
				genre = "";
				questions.add(question);
			}
			
			pageInfo.setQuestions(questions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}

	void NameRequire(String genreID, String sortRule, String UserID){
		Connection con = connectSQL.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("select * from question WHERE  QuestionGenre like (?) and QuestionID in (select AnswerOfQuestion from answer where AnswerUserID=?)");
			pstmt.setString(1, "%"+genreID+"%");
			pstmt.setString(2, UserID);
			ResultSet rs = pstmt.executeQuery();
			rs.last();
			pageInfo.setPageSum(rs.getRow()%9==0?rs.getRow()/9:rs.getRow()/9+1);

			pstmt = con.prepareStatement("select QuestionID,QuestionTheme,QuestionUserID,QuestionGenre,QuestionEndTime,QuestionMoney,QuestionPageView  " +
					"from question WHERE QuestionGenre like (?) and QuestionID in (select AnswerOfQuestion from answer where AnswerUserID=?)" + sortRule +
					"limit ?,9");
			pstmt.setString(1, "%"+genreID+"%");
			pstmt.setString(2, UserID);
			pstmt.setInt(3, (pagenum-1)*9);
			
			ResultSet rs4 = pstmt.executeQuery();
			while(rs4.next()){
				Question question = new Question();
				question.setQuestionID(rs4.getString(1));
				question.setQuestionTheme(rs4.getString(2));
				question.setQuestionUserID(rs4.getString(3));
				genres = rs4.getString(4).split(",");
				question.setQuestionEndTime(rs4.getString(5));
				question.setQuestionMoney(rs4.getString(6));
				question.setQuestionPageView(rs4.getInt(7));
				pstmt = con.prepareStatement("select UserName from user where UserID=?");
				pstmt.setString(1, question.getQuestionUserID());
				ResultSet rs2 = pstmt.executeQuery();
				if(rs2.next())
					question.setQuestionUsername(rs2.getString(1));
				pstmt = con.prepareStatement("select count(*) from answer where AnswerOfQuestion=?");
				pstmt.setString(1, question.getQuestionID());
				rs2 = pstmt.executeQuery();
				if(rs2.next()){
					question.setQuestionAnwserQuantity(rs2.getInt(1));
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
				question.setQuestionGenre(genre);
				genre = "";
				questions.add(question);
			}
			pageInfo.setQuestions(questions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connectSQL.closeConnection(con);
			connectSQL.closePreparedStatement(pstmt);
		}
	}
}
