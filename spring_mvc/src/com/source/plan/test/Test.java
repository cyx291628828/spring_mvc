package com.source.plan.test;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.source.plan.entity.Answer;
import com.source.plan.entity.AnswerDAO;
import com.source.plan.entity.Discuss;
import com.source.plan.entity.DiscussDAO;
import com.source.plan.entity.Dowload;
import com.source.plan.entity.DowloadDAO;
import com.source.plan.entity.User;
import com.source.plan.entity.UserDAO;
import com.source.plan.hibernate.HibernateSessionFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		UserDAO udaoDao = new UserDAO();
		DowloadDAO dowloadDAO = new DowloadDAO();
		AnswerDAO answerDAO = new AnswerDAO();
		DiscussDAO discussDAO = new DiscussDAO();
		List<User> findAll = udaoDao.findAll();
		Dowload dowload = new Dowload();
//		user.setUserId("223322335566");
//		user.setUserName("没ID得名字");
//		udaoDao.save(user);
		
//		List<Discuss> listDiscusses = discussDAO.findByProperty("isTheme", 1);
//		System.out.println(listDiscusses.size());
//		Discuss discuss1= new Discuss();
//		Query findQueryByHQL = discussDAO.findQueryByHQL("from Discuss where ThemeDiscussID = ? order by LastTime desc");
//		findQueryByHQL.setParameter(0, "0000000010");
//		findQueryByHQL.setMaxResults(1);
//		Discuss list = discussDAO.findById("0000000008");
//		Object[] array = list.getDiscussesForReplyDiscussId().toArray();
//		//Discuss[] disa = (Discuss[]) array;
//		System.out.println(((Discuss)array[0]).getUser().getUserName());
//		String id = "00000000";
//		for(int i = 16 ; i < 17 ; i++){
//			id = "00000000";
//			id = id + i;
//			Discuss findById = discussDAO.findById(id);
//			findById.setDiscussByReplyDiscussId(null);
//			findById.setDiscussByThemeDiscussId(null);
//			discussDAO.save(findById);
//			
//		}
//		discuss1 = listDiscusses.get(0);
//		for(int i = 0 ; i < 5 ; i++){
//			Discuss discuss= new Discuss();
//			discuss = discuss1;
//			System.out.println(discuss.getUser().getUserName());
//			discuss1 = discuss1.getDiscussByReplyDiscussId();
//		}
		
//		List<Dowload> dowloads = dowloadDAO.findByHQL("from Dowload as d join fetch d.answer as a where a.answerId = '0000000008'");
//		for(Dowload answer : dowloads){
//			System.out.println(answer.getQuestion().getUser().getUserName());
//		}
//		for(User uuUser : findAll){
//			System.out.println(uuUser.getUserName());
//		}
		

//		for(User user2 : findByExample){
//			System.out.println(user2.getUserName());
//		}
		
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = session.beginTransaction();
//		transaction.begin();
//		user = findAll.get(0);
//		user.setUserName("更新的名字");
//		session.save(user);
//		transaction.commit();
	}

}
