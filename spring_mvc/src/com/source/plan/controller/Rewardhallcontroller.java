package com.source.plan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.entity.Answer;
import com.source.plan.entity.AnswerDAO;
import com.source.plan.entity.Genre;
import com.source.plan.entity.GenreDAO;
import com.source.plan.entity.PageInfo;
import com.source.plan.entity.Question;
import com.source.plan.entity.QuestionDAO;

@Controller
public class Rewardhallcontroller {

	int pagenum = 0;
	private String[] genres = new String[10];
	QuestionDAO questionDAO = new QuestionDAO();
	AnswerDAO answerDAO = new AnswerDAO();
	GenreDAO genreDAO = new GenreDAO();
	PageInfo pageInfo = new PageInfo();
	List<Question> questions_req = null;
	
	@RequestMapping(value="/RewardHall")
	public String rewardHall(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String genreID = request.getParameter("genreID");
		String genreType = request.getParameter("genreType")==null?"0":request.getParameter("genreType");
		String sortRule = request.getParameter("sortRule")==null?"0":request.getParameter("sortRule");
		sortRule = getSortRule(Integer.parseInt(sortRule));
		List<Genre> genres = new ArrayList<Genre>();
		List<Question> questions = new ArrayList<Question>();
		String type = "%";
		String sql="select * from genre ";
		pagenum = request.getParameter("pageNum")==null?1:Integer.parseInt(request.getParameter("pageNum"));
		System.out.println("genreType="+genreType);
		try{
			if(genreID != null && !genreID.equals("0")){
				List<Genre> list_Genres = genreDAO.findByProperty("GenreID", genreID);
				if(list_Genres != null){
					genreType = list_Genres.get(0).getGenreType();
				}
			}
			System.out.println("genreType="+genreType);
			List<Genre> list_like_genre = genreDAO.findBySQL("from Genre where GenreType like '"+ type +"'");
			pageInfo.setGenres(list_like_genre);
			if(genreID == null || genreID.equals("0")){
				//如果语言名称和语言分类都为空 那么自动设置为全部
				TypeRequire(type,sortRule);	
			}else {
				//其他以语言名称为搜索条件
				NameRequire(genreID,sortRule);
			}
			pageInfo.setPageNum(pagenum);
			request.setAttribute("pageInfo", pageInfo);
			request.getRequestDispatcher("question/RewardHall.jsp?genreType="+genreType+"&genreID="+genreID).forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		return "login/MyLog";
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
	void TypeRequire(String genreType, String sortRule){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = " ";
		
		try {
			List<Genre> list_genre = genreDAO.findBySQL("from Genre where GenreType like '"+ genreType +"'");
			int i = 0;
			for(Genre genres : list_genre){
				if(i == 0){
					sql += " QuestionGenre like ('%" + genres.getGenreId() + "%') ";
				}
				else {
					sql += " or QuestionGenre like ('%" + genres.getGenreId() + "%') ";
				}
				i++;
			}
			List<Question> questions = questionDAO.findBySQL("from question where ( "+sql + ") and QuestionEndTime > '" + sdf.format(new Date()) +"'");
			pageInfo.setPageSum(questions.size()%9==0?questions.size()/9:questions.size()/9+1);
			
		
			List<Question> questions2 = questionDAO.findBySQL("from question where ( "+sql +") "  +" and QuestionEndTime > '" + sdf.format(new Date()) +"' "+sortRule +" limit "+(pagenum-1)*9+" ,9");
			for(Question q : questions2){
				String genreString = "";
				List<Answer> answers = answerDAO.findBySQL("from answer where AnswerOfQuestion= " + q.getQuestionId());
				q.setQuestionAnwserQuantity(answers.size());
				genres = q.getQuestionGenre().split(",");
				for(int j = 0; j < genres.length; j++)
				{
					List<Genre> lists = genreDAO.findBySQL("from genre where GenreID= "+genres[j]);
					genreString +=lists.get(0).getGenreName();
					if(j < genres.length-1)
						genreString +=",";
				}
				q.setQuestionGenre(genreString);
				questions_req.add(q);
			}

			pageInfo.setQuestions(questions_req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void NameRequire(String genreID, String sortRule){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
		PreparedStatement pstmt = null;
		try {
			List<Question> questions = questionDAO.findBySQL("from question WHERE  QuestionGenre like (%"+genreID+"%) and QuestionEndTime > '" + sdf.format(new Date()) +"'" );
			pageInfo.setPageSum(questions.size()%9==0?questions.size()/9:questions.size()/9+1);
			System.out.println("select * from question WHERE  QuestionGenre like ("+"%"+genreID+"%"+") and QuestionEndTime > '" + sdf.format(new Date()) +
					"'" );
			questions = questionDAO.findBySQL("from question WHERE QuestionGenre like (?)"  + " and QuestionEndTime > '" + sdf.format(new Date()) + 
					"'"+ sortRule+" limit "+(pagenum-1)*9+",9");
			
			for(Question q : questions){
				String genreString = "";
				List<Answer> answers = answerDAO.findBySQL("from answer where AnswerOfQuestion= " + q.getQuestionId());
				q.setQuestionAnwserQuantity(answers.size());
				genres = q.getQuestionGenre().split(",");
				for(int i = 0; i < genres.length;i++)
				{
					List<Genre> lists = genreDAO.findBySQL("from genre where GenreID= "+genres[i]);
					genreString +=lists.get(0).getGenreName();
					if(i < genres.length-1)
						genreString +=",";
				}
				q.setQuestionGenre(genreString);
				questions_req.add(q);
			}
			pageInfo.setQuestions(questions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
