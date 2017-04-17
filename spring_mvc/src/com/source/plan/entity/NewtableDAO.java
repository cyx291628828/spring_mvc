package com.source.plan.entity;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Newtable entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.source.plan.entity.Newtable
 * @author MyEclipse Persistence Tools
 */
public class NewtableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(NewtableDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(Newtable transientInstance) {
		log.debug("saving Newtable instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			transaction.commit();
			session.close();
		}
	}

	public void delete(Newtable persistentInstance) {
		log.debug("deleting Newtable instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			transaction.commit();
			session.close();
		}
	}

	public Newtable findById(java.lang.Integer id) {
		log.debug("getting Newtable instance with id: " + id);
		try {
			Newtable instance = (Newtable) getSession().get(
					"com.source.plan.entity.Newtable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Newtable instance) {
		log.debug("finding Newtable instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Newtable")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Newtable instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Newtable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Newtable instances");
		try {
			String queryString = "from Newtable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Newtable merge(Newtable detachedInstance) {
		log.debug("merging Newtable instance");
		try {
			Newtable result = (Newtable) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Newtable instance) {
		log.debug("attaching dirty Newtable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Newtable instance) {
		log.debug("attaching clean Newtable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}