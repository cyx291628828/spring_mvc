package com.source.plan.entity;

import java.util.List;

import com.source.plan.hibernate.HibernateSessionFactory;

import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	public List findBySQL(String sql) {
		try {
			Query queryObject = getSession().createQuery(sql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
}