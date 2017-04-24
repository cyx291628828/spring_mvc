package com.source.plan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.source.plan.entity.Answer;
import com.source.plan.entity.AnswerDAO;
import com.source.plan.entity.Dowload;
import com.source.plan.entity.DowloadDAO;
import com.source.plan.entity.Genre;
import com.source.plan.entity.GenreDAO;
import com.source.plan.entity.Newtable;
import com.source.plan.entity.PageInfo;
import com.source.plan.entity.Question;
import com.source.plan.entity.QuestionDAO;
import com.source.plan.entity.User;
import com.source.plan.entity.UserDAO;
import com.source.plan.jdbc.connectSQL;
import com.source.plan.vcode.ValidateCode;


@Controller
public class DownloadContorller {
	
	int pagenum = 0;
	private String[] genres = new String[10];
	QuestionDAO questionDAO = new QuestionDAO();
	AnswerDAO answerDAO = new AnswerDAO();
	GenreDAO genreDAO = new GenreDAO();
	DowloadDAO downloadDAO = new DowloadDAO();
	PageInfo pageInfo = new PageInfo();
	List<Question> questions_req = new ArrayList<Question>();
	List<Answer> answer_req = new ArrayList<Answer>();
	List<Dowload> Downloads = null;
	private String genre = "";
	public void init(){
		pagenum = 0;
		genres = new String[10];
		questionDAO = new QuestionDAO();
		answerDAO = new AnswerDAO();
		genreDAO = new GenreDAO();
		downloadDAO = new DowloadDAO();
		pageInfo = new PageInfo();
		questions_req = new ArrayList<Question>();
		answer_req =  new ArrayList<Answer>();
		Downloads = new ArrayList<Dowload>();
		genre = "";
	}
	
	@RequestMapping(value="/Download")
	public String download(HttpServletRequest request, HttpServletResponse response) throws IOException{
		init();
		String genreID = request.getParameter("genreID");
		String genreType = request.getParameter("genreType")==null?"0":request.getParameter("genreType");
		String sortRule = request.getParameter("sortRule")==null?"0":request.getParameter("sortRule");
		sortRule = getSortRule(Integer.parseInt(sortRule));
		List<Genre> genres = new ArrayList<Genre>();
		pageInfo = new PageInfo();
		String type = "%";
		pagenum = request.getParameter("pageNum")==null?1:Integer.parseInt(request.getParameter("pageNum"));
		
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
			return "download/download";
		}catch (Exception e) {
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
		case 5:sql = " order by QuestionDownMoney ";break;
		case 6:sql = " order by QuestionDownMoney desc ";break;
		}
		return sql;
		
	}
	
	void TypeRequire(String genreType, String sortRule){
		String sql = "";
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
			List<Answer> answers = answerDAO.findByHQL("from Answer as a join fetch a.question as q where q.questionDownMoney > -1 and a.answerIsSelect = 1 and ("+sql+")");
			pageInfo.setPageSum(answers.size()%9==0?answers.size()/9:answers.size()/9+1);
			
			List<Answer> answers2 = new ArrayList<Answer>();
			Query queryAnswer = answerDAO.findQueryByHQL("from Answer as a join fetch a.question as q where q.questionDownMoney > -1 and a.answerIsSelect = 1 and ("+sql+")"+sortRule);
			queryAnswer.setFirstResult((pagenum-1)*9);
			queryAnswer.setMaxResults(9);
			answers2 = queryAnswer.list();
			for(Answer answer : answers2){
				List<Dowload> dowloads = downloadDAO.findByHQL("from Dowload as d join fetch d.answer as a where a.answerId = '"+answer.getAnswerId()+"'");
				int zan = 0;
				int pinglun = 0;
				for(Dowload d : dowloads){
					if(d.getIsZan() == 1){
						zan++;
					}
					if(d.getIsPing() == 1){
						pinglun++;
					}
				}
				answer.setZan_num(zan);
				answer.setPinglun_num(pinglun);
				answer.setDownload_num(dowloads.size());
				answer_req.add(answer);
			}
			pageInfo.setAnswers(answer_req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void NameRequire(String genreID, String sortRule){
		try {
			List<Answer> answers = answerDAO.findByHQL("from Answer as a join fetch a.question as q where q.questionDownMoney > -1 and a.answerIsSelect = 1 and q.questionGenre like '%"+genreID+"%'");
			pageInfo.setPageSum(answers.size()%9==0?answers.size()/9:answers.size()/9+1);

			List<Answer> answers2 = new ArrayList<Answer>();
			Query queryAnswer = answerDAO.findQueryByHQL("from Answer as a join fetch a.question as q where q.questionDownMoney > -1 and a.answerIsSelect = 1 and q.questionGenre like '%"+genreID+"%' "+sortRule);
			queryAnswer.setFirstResult((pagenum-1)*9);
			queryAnswer.setMaxResults(9);
			answers2 = queryAnswer.list();
			for(Answer answer : answers2){
				Answer a = new Answer();
				List<Dowload> dowloads = downloadDAO.findByHQL("from Dowload as d join fetch d.answer as a where a.answerId = '"+answer.getAnswerId()+"'");
				int zan = 0;
				int pinglun = 0;
				for(Dowload d : dowloads){
					if(d.getIsZan() == 1){
						zan++;
					}
					if(d.getIsPing() == 1){
						pinglun++;
					}
				}
				answer.setZan_num(zan);
				answer.setPinglun_num(pinglun);
				answer.setDownload_num(dowloads.size());
				answer_req.add(answer);
			}
			pageInfo.setAnswers(answers2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
