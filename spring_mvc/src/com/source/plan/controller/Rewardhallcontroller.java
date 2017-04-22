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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.entity.Answer;
import com.source.plan.entity.AnswerDAO;
import com.source.plan.entity.Genre;
import com.source.plan.entity.GenreDAO;
import com.source.plan.entity.Newtable;
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
	List<Question> questions_req = new ArrayList<Question>();
	public void init(){
		pagenum = 0;
		genres = new String[10];
		questionDAO = new QuestionDAO();
		answerDAO = new AnswerDAO();
		genreDAO = new GenreDAO();
		pageInfo = new PageInfo();
		questions_req = new ArrayList<Question>();
	}
	
	@RequestMapping(value="/RewardHall")
	public String rewardHall(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		init();
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
			switch (Integer.parseInt(genreType)) {
			case 1:
				type="后端开发";
				break;
			case 2:
				type="前端开发";
				break;
			case 3:
				type="移动开发";
				break;
			case 4:
				type="其他";
				break;
			default:
				break;
			}
			if(genreID != null && !genreID.equals("0")){
				Genre gen_ById = genreDAO.findById(genreID);
				if(gen_ById != null){
					genreType = gen_ById.getGenreType();
				}
			}
			System.out.println("genreType="+genreType);
			List<Genre> list_like_genre = genreDAO.findByHQL("from Genre where GenreType like '"+ type +"'");
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
			request.setAttribute("genreType", genreType);
			request.setAttribute("genreID", genreID);
			return "question/RewardHall";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		return "index";
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

	void TypeRequire(String genreType, String sortRule){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = " ";
		
		try {
			List<Genre> list_genre = genreDAO.findByHQL("from Genre where GenreType like '"+ genreType +"'");
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
			List<Question> questions = questionDAO.findByHQL("from Question where ( "+sql + ") and QuestionEndTime > '" + sdf.format(new Date()) +"'");
			pageInfo.setPageSum(questions.size()%9==0?questions.size()/9:questions.size()/9+1);
			
			List<Question> questions2 = new ArrayList<Question>();
			Query queryQuestion = questionDAO.findQueryByHQL("from Question where ( "+sql +") and QuestionEndTime > '" + sdf.format(new Date()) +"'" +sortRule);
			queryQuestion.setFirstResult((pagenum-1)*9);
			queryQuestion.setMaxResults(9);
			questions2 = queryQuestion.list();
			for(Question q : questions2){
				String genreString = "";
				List<Answer> answers = answerDAO.findByHQL("from Answer where AnswerOfQuestion= " + q.getQuestionId());
				q.setQuestionAnwserQuantity(answers.size());
				genres = q.getQuestionGenre().split(",");
				for(int j = 0; j < genres.length; j++)
				{
					List<Genre> lists = genreDAO.findByHQL("from Genre where GenreID= "+genres[j]);
					genreString +=lists.get(0).getGenreName();
					if(j < genres.length-1)
						genreString +=",";
				}
				q.setString_Genre(genreString);
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
			List<Question> questions = questionDAO.findByHQL("from Question WHERE  QuestionGenre like ('%"+genreID+"%') and QuestionEndTime > '" + sdf.format(new Date()) +"'" );
			pageInfo.setPageSum(questions.size()%9==0?questions.size()/9:questions.size()/9+1);
			System.out.println("select * from question WHERE  QuestionGenre like ('%"+genreID+"%') and QuestionEndTime > '" + sdf.format(new Date()) +
					"'" );
			List<Question> questions2 = new ArrayList<Question>();
			Query queryQuestion = questionDAO.findQueryByHQL("from Question WHERE QuestionGenre like ('%"+genreID+"%')"  + " and QuestionEndTime > '" + sdf.format(new Date()) + 
					"'"+ sortRule);
			queryQuestion.setFirstResult((pagenum-1)*9);
			queryQuestion.setMaxResults(9);
			questions2 = queryQuestion.list();
			for(Question q : questions2){
				String genreString = "";
				List<Answer> answers = answerDAO.findByHQL("from Answer where AnswerOfQuestion= " + q.getQuestionId());
				q.setQuestionAnwserQuantity(answers.size());
				genres = q.getQuestionGenre().split(",");
				for(int i = 0; i < genres.length;i++)
				{
					List<Genre> lists = genreDAO.findByHQL("from Genre where GenreID= "+genres[i]);
					genreString +=lists.get(0).getGenreName();
					if(i < genres.length-1)
						genreString +=",";
				}
				q.setString_Genre(genreString);
				questions_req.add(q);
			}
			pageInfo.setQuestions(questions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
