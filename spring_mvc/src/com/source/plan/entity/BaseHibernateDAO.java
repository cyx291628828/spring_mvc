package com.source.plan.entity;

import java.util.List;

import com.source.plan.hibernate.HibernateSessionFactory;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	/**
	 * 完整的sql语句，表明使用映射的对象名。不要select *
	 * @param sql
	 * @return
	 */
	public List findByHQL(String sql) {
		try {
			Query queryObject = getSession().createQuery(sql);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Query findQueryByHQL(String sql) {
		try {
			Query queryObject = getSession().createQuery(sql);
			return queryObject;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public SQLQuery findBySQL(String sql){
		return getSession().createSQLQuery(sql);
	}
	
	public List findListBySQL(String sql){
		try {
			SQLQuery query = getSession().createSQLQuery(sql);
			return query.list();
		} catch (RuntimeException e) {
			throw e;
		}
	}
}