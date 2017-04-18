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
 * Question entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.source.plan.entity.Question
 * @author MyEclipse Persistence Tools
 */
public class QuestionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(QuestionDAO.class);
	// property constants
	public static final String QUESTION_THEME = "questionTheme";
	public static final String QUESTION_CONTENT = "questionContent";
	public static final String QUESTION_STA_TIME = "questionStaTime";
	public static final String QUESTION_END_TIME = "questionEndTime";
	public static final String QUESTION_FIN_TIME = "questionFinTime";
	public static final String QUESTION_GENRE = "questionGenre";
	public static final String QUESTION_FILE = "questionFile";
	public static final String QUESTION_MONEY = "questionMoney";
	public static final String QUESTION_IS_FINISH = "questionIsFinish";
	public static final String QUESTION_IS_DOWNLOAD = "questionIsDownload";
	public static final String QUESTION_DOWN_MONEY = "questionDownMoney";
	public static final String QUESTION_PAGE_VIEW = "questionPageView";

	public void save(Question transientInstance) {
		log.debug("saving Question instance");
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

	public void delete(Question persistentInstance) {
		log.debug("deleting Question instance");
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

	public Question findById(java.lang.String id) {
		log.debug("getting Question instance with id: " + id);
		try {
			Question instance = (Question) getSession().get(
					"com.source.plan.entity.Question", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Question instance) {
		log.debug("finding Question instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Question")
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
		log.debug("finding Question instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Question as model where model."
					+ propertyName + "> ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByQuestionTheme(Object questionTheme) {
		return findByProperty(QUESTION_THEME, questionTheme);
	}

	public List findByQuestionContent(Object questionContent) {
		return findByProperty(QUESTION_CONTENT, questionContent);
	}

	public List findByQuestionStaTime(Object questionStaTime) {
		return findByProperty(QUESTION_STA_TIME, questionStaTime);
	}

	public List findByQuestionEndTime(Object questionEndTime) {
		return findByProperty(QUESTION_END_TIME, questionEndTime);
	}

	public List findByQuestionFinTime(Object questionFinTime) {
		return findByProperty(QUESTION_FIN_TIME, questionFinTime);
	}

	public List findByQuestionGenre(Object questionGenre) {
		return findByProperty(QUESTION_GENRE, questionGenre);
	}

	public List findByQuestionFile(Object questionFile) {
		return findByProperty(QUESTION_FILE, questionFile);
	}

	public List findByQuestionMoney(Object questionMoney) {
		return findByProperty(QUESTION_MONEY, questionMoney);
	}

	public List findByQuestionIsFinish(Object questionIsFinish) {
		return findByProperty(QUESTION_IS_FINISH, questionIsFinish);
	}

	public List findByQuestionIsDownload(Object questionIsDownload) {
		return findByProperty(QUESTION_IS_DOWNLOAD, questionIsDownload);
	}

	public List findByQuestionDownMoney(Object questionDownMoney) {
		return findByProperty(QUESTION_DOWN_MONEY, questionDownMoney);
	}

	public List findByQuestionPageView(Object questionPageView) {
		return findByProperty(QUESTION_PAGE_VIEW, questionPageView);
	}

	public List findAll() {
		log.debug("finding all Question instances");
		try {
			String queryString = "from Question";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Question merge(Question detachedInstance) {
		log.debug("merging Question instance");
		try {
			Question result = (Question) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Question instance) {
		log.debug("attaching dirty Question instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Question instance) {
		log.debug("attaching clean Question instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}