package com.source.plan.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.source.plan.entity.Discuss;
import com.source.plan.entity.DiscussDAO;


@Controller
public class ReplyContorller {
	
	@RequestMapping(value="/Reply")
	public String reply(@ModelAttribute("DiscussID") String DiscussID,HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			//String DiscussID = request.getParameter("DiscussID");
			DiscussDAO discussDAO = new DiscussDAO();
			List<Discuss> list_discuss = new ArrayList<Discuss>();
			Integer pageSum = 0;
			String pageNum1 = request.getParameter("pageNum");
			Integer pageNum = pageNum1 == null ? 1 : Integer.parseInt(pageNum1);
			if(DiscussID == null || DiscussID.length() < 10){
				List<Discuss> listDiscusses = new ArrayList<Discuss>();
				listDiscusses = discussDAO.findByProperty("isTheme", 1);
				pageSum = listDiscusses.size();
				Query queryDiscuss = discussDAO.findQueryByHQL("from Discuss where Istheme = 1 order by LastTime Desc");
				queryDiscuss.setFirstResult((pageNum-1)*20);
				queryDiscuss.setMaxResults(20);
				listDiscusses = new ArrayList<Discuss>();
				listDiscusses = queryDiscuss.list();
				for(Discuss dis : listDiscusses){
					Query findQueryByHQL = discussDAO.findQueryByHQL("from Discuss where ThemeDiscussID = ? order by LastTime desc");
					findQueryByHQL.setParameter(0, dis.getDiscussId());
					findQueryByHQL.setMaxResults(1);
					List<Discuss> list = findQueryByHQL.list();
					if(list.size() > 0){
						dis.setRe_lastTime(list.get(0).getLastTime());
						dis.setRe_lastName(list.get(0).getUser().getUserName());
					}else{
						dis.setRe_lastTime(dis.getLastTime());
						dis.setRe_lastName(dis.getUser().getUserName());
					}
				}
				sortStringMethod(listDiscusses);//按最后的回复时间排序
				request.setAttribute("pageSum",pageSum);//传消息个数
				request.setAttribute("list_discuss",listDiscusses);
				request.setAttribute("pageNum",pageNum);//传当前页面
				return "discuss/Discuss";
			}else{
				Discuss discussById = discussDAO.findById(""+DiscussID);
				pageSum = discussById.getDiscussesForReplyDiscussId().size();
				request.setAttribute("pageSum",pageSum);//传消息个数
				request.setAttribute("discuss",discussById);//把消息传回jsp页面
				request.setAttribute("pageNum",pageNum);//传当前页面
				return "discuss/intoReply";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	@SuppressWarnings("unchecked")
	private void sortStringMethod(List<Discuss> listDiscusses) {
		Collections.sort(listDiscusses, new Comparator(){
			public int compare(Object o1, Object o2) {  
	        	Discuss dis1=(Discuss)o1;  
	        	Discuss dis2=(Discuss)o2;  
	            return dis2.getRe_lastTime().compareTo(dis1.getRe_lastTime());  
	        }             
	    });
		
	}
	
}
