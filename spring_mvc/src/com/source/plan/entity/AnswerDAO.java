package com.source.plan.entity;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.source.plan.hibernate.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Answer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.source.plan.entity.Answer
 * @author MyEclipse Persistence Tools
 */
public class AnswerDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AnswerDAO.class);
	// property constants
	public static final String ANSWER_CONTENT = "answerContent";
	public static final String ANSWER_TIME = "answerTime";
	public static final String ANSWER_FILE = "answerFile";
	public static final String ANSWER_IS_SELECT = "answerIsSelect";

	public void save(Answer transientInstance) {
		log.debug("saving Answer instance");
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

	public void delete(Answer persistentInstance) {
		log.debug("deleting Answer instance");
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

	public Answer findById(java.lang.String id) {
		log.debug("getting Answer instance with id: " + id);
		try {
			Answer instance = (Answer) getSession().get(
					"com.source.plan.entity.Answer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Answer instance) {
		log.debug("finding Answer instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Answer")
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
		log.debug("finding Answer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Answer as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAnswerContent(Object answerContent) {
		return findByProperty(ANSWER_CONTENT, answerContent);
	}

	public List findByAnswerTime(Object answerTime) {
		return findByProperty(ANSWER_TIME, answerTime);
	}

	public List findByAnswerFile(Object answerFile) {
		return findByProperty(ANSWER_FILE, answerFile);
	}

	public List findByAnswerIsSelect(Object answerIsSelect) {
		return findByProperty(ANSWER_IS_SELECT, answerIsSelect);
	}

	public List findAll() {
		log.debug("finding all Answer instances");
		try {
			String queryString = "from Answer";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Answer merge(Answer detachedInstance) {
		log.debug("merging Answer instance");
		try {
			Answer result = (Answer) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Answer instance) {
		log.debug("attaching dirty Answer instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Answer instance) {
		log.debug("attaching clean Answer instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}