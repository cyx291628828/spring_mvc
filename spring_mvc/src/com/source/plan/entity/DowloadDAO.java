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
 * Dowload entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.source.plan.entity.Dowload
 * @author MyEclipse Persistence Tools
 */
public class DowloadDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DowloadDAO.class);
	// property constants
	public static final String DOWN_TIME = "downTime";
	public static final String DOWN_MONEY = "downMoney";
	public static final String IS_ZAN = "isZan";
	public static final String IS_PING = "isPing";
	public static final String PING_CONCENT = "pingConcent";
	public static final String PING_TIME = "pingTime";

	public void save(Dowload transientInstance) {
		log.debug("saving Dowload instance");
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

	public void delete(Dowload persistentInstance) {
		log.debug("deleting Dowload instance");
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

	public Dowload findById(java.lang.String id) {
		log.debug("getting Dowload instance with id: " + id);
		try {
			Dowload instance = (Dowload) getSession().get(
					"com.source.plan.entity.Dowload", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Dowload instance) {
		log.debug("finding Dowload instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Dowload")
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
		log.debug("finding Dowload instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dowload as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDownTime(Object downTime) {
		return findByProperty(DOWN_TIME, downTime);
	}

	public List findByDownMoney(Object downMoney) {
		return findByProperty(DOWN_MONEY, downMoney);
	}

	public List findByIsZan(Object isZan) {
		return findByProperty(IS_ZAN, isZan);
	}

	public List findByIsPing(Object isPing) {
		return findByProperty(IS_PING, isPing);
	}

	public List findByPingConcent(Object pingConcent) {
		return findByProperty(PING_CONCENT, pingConcent);
	}

	public List findByPingTime(Object pingTime) {
		return findByProperty(PING_TIME, pingTime);
	}

	public List findAll() {
		log.debug("finding all Dowload instances");
		try {
			String queryString = "from Dowload";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Dowload merge(Dowload detachedInstance) {
		log.debug("merging Dowload instance");
		try {
			Dowload result = (Dowload) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dowload instance) {
		log.debug("attaching dirty Dowload instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dowload instance) {
		log.debug("attaching clean Dowload instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}