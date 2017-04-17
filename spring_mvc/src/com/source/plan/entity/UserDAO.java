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
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.source.plan.entity.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String USER_PASS = "userPass";
	public static final String USER_REG_TIME = "userRegTime";
	public static final String USER_SEX = "userSex";
	public static final String USER_BIRTH = "userBirth";
	public static final String USER_JOB = "userJob";
	public static final String USER_FAKE_MONEY = "userFakeMoney";
	public static final String USER_INTEREST = "userInterest";
	public static final String USER_LEVEL = "userLevel";
	public static final String USER_IMAGE = "userImage";
	public static final String USER_ANSWER_NUM = "userAnswerNum";
	public static final String USER_ANSWER_SCS_NUM = "userAnswerScsNum";

	public void save(User transientInstance) {
		log.debug("saving User instance");
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

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
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

	public User findById(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get(
					"com.source.plan.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserPass(Object userPass) {
		return findByProperty(USER_PASS, userPass);
	}

	public List findByUserRegTime(Object userRegTime) {
		return findByProperty(USER_REG_TIME, userRegTime);
	}

	public List findByUserSex(Object userSex) {
		return findByProperty(USER_SEX, userSex);
	}

	public List findByUserBirth(Object userBirth) {
		return findByProperty(USER_BIRTH, userBirth);
	}

	public List findByUserJob(Object userJob) {
		return findByProperty(USER_JOB, userJob);
	}

	public List findByUserFakeMoney(Object userFakeMoney) {
		return findByProperty(USER_FAKE_MONEY, userFakeMoney);
	}

	public List findByUserInterest(Object userInterest) {
		return findByProperty(USER_INTEREST, userInterest);
	}

	public List findByUserLevel(Object userLevel) {
		return findByProperty(USER_LEVEL, userLevel);
	}

	public List findByUserImage(Object userImage) {
		return findByProperty(USER_IMAGE, userImage);
	}

	public List findByUserAnswerNum(Object userAnswerNum) {
		return findByProperty(USER_ANSWER_NUM, userAnswerNum);
	}

	public List findByUserAnswerScsNum(Object userAnswerScsNum) {
		return findByProperty(USER_ANSWER_SCS_NUM, userAnswerScsNum);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}