package com.source.plan.entity;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Discuss entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.source.plan.entity.Discuss
 * @author MyEclipse Persistence Tools
 */
public class DiscussDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DiscussDAO.class);
	// property constants
	public static final String DISCUSS_TIME = "discussTime";
	public static final String DISCUSS_CONTENT = "discussContent";
	public static final String IS_THEME = "isTheme";
	public static final String IS_REPLY = "isReply";
	public static final String LAST_TIME = "lastTime";

	public void save(Discuss transientInstance) {
		log.debug("saving Discuss instance");
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

	public void delete(Discuss persistentInstance) {
		log.debug("deleting Discuss instance");
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

	public Discuss findById(java.lang.String id) {
		log.debug("getting Discuss instance with id: " + id);
		try {
			Discuss instance = (Discuss) getSession().get(
					"com.source.plan.entity.Discuss", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Discuss instance) {
		log.debug("finding Discuss instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Discuss")
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
		log.debug("finding Discuss instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Discuss as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDiscussTime(Object discussTime) {
		return findByProperty(DISCUSS_TIME, discussTime);
	}

	public List findByDiscussContent(Object discussContent) {
		return findByProperty(DISCUSS_CONTENT, discussContent);
	}

	public List findByIsTheme(Object isTheme) {
		return findByProperty(IS_THEME, isTheme);
	}

	public List findByIsReply(Object isReply) {
		return findByProperty(IS_REPLY, isReply);
	}

	public List findByLastTime(Object lastTime) {
		return findByProperty(LAST_TIME, lastTime);
	}

	public List findAll() {
		log.debug("finding all Discuss instances");
		try {
			String queryString = "from Discuss";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Discuss merge(Discuss detachedInstance) {
		log.debug("merging Discuss instance");
		try {
			Discuss result = (Discuss) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Discuss instance) {
		log.debug("attaching dirty Discuss instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Discuss instance) {
		log.debug("attaching clean Discuss instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}