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
 * Notice entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.source.plan.entity.Notice
 * @author MyEclipse Persistence Tools
 */
public class NoticeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(NoticeDAO.class);
	// property constants
	public static final String NOTICE_CONTENT = "noticeContent";
	public static final String NOTICE_ARISE_TIME = "noticeAriseTime";
	public static final String NOTICE_IS_CHECK = "noticeIsCheck";

	public void save(Notice transientInstance) {
		log.debug("saving Notice instance");
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

	public void delete(Notice persistentInstance) {
		log.debug("deleting Notice instance");
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

	public Notice findById(java.lang.String id) {
		log.debug("getting Notice instance with id: " + id);
		try {
			Notice instance = (Notice) getSession().get(
					"com.source.plan.entity.Notice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Notice instance) {
		log.debug("finding Notice instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Notice")
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
		log.debug("finding Notice instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Notice as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoticeContent(Object noticeContent) {
		return findByProperty(NOTICE_CONTENT, noticeContent);
	}

	public List findByNoticeAriseTime(Object noticeAriseTime) {
		return findByProperty(NOTICE_ARISE_TIME, noticeAriseTime);
	}

	public List findByNoticeIsCheck(Object noticeIsCheck) {
		return findByProperty(NOTICE_IS_CHECK, noticeIsCheck);
	}

	public List findAll() {
		log.debug("finding all Notice instances");
		try {
			String queryString = "from Notice";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Notice merge(Notice detachedInstance) {
		log.debug("merging Notice instance");
		try {
			Notice result = (Notice) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Notice instance) {
		log.debug("attaching dirty Notice instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Notice instance) {
		log.debug("attaching clean Notice instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}