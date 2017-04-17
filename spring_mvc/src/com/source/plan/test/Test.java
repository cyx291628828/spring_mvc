package com.source.plan.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
		List<User> findAll = udaoDao.findAll();
		for(User uuUser : findAll){
			System.out.println(uuUser.getUserName());
		}
		
		user.setUserId("222221222");
		user.setUserName("加上去的名字");
		udaoDao.delete(findAll.get(0));
		
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = session.beginTransaction();
//		transaction.begin();
//		user = findAll.get(0);
//		user.setUserName("更新的名字");
//		session.save(user);
//		transaction.commit();
	}

}
