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
 * A data access object (DAO) providing persistence and search support for Genre
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.source.plan.entity.Genre
 * @author MyEclipse Persistence Tools
 */
public class GenreDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GenreDAO.class);
	// property constants
	public static final String GENRE_NAME = "genreName";
	public static final String GENRE_TYPE = "genreType";

	public void save(Genre transientInstance) {
		log.debug("saving Genre instance");
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

	public void delete(Genre persistentInstance) {
		log.debug("deleting Genre instance");
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

	public Genre findById(java.lang.String id) {
		log.debug("getting Genre instance with id: " + id);
		try {
			Genre instance = (Genre) getSession().get(
					"com.source.plan.entity.Genre", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Genre instance) {
		log.debug("finding Genre instance by example");
		try {
			List results = getSession()
					.createCriteria("com.source.plan.entity.Genre")
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
		log.debug("finding Genre instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Genre as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGenreName(Object genreName) {
		return findByProperty(GENRE_NAME, genreName);
	}

	public List findByGenreType(Object genreType) {
		return findByProperty(GENRE_TYPE, genreType);
	}

	public List findAll() {
		log.debug("finding all Genre instances");
		try {
			String queryString = "from Genre";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Genre merge(Genre detachedInstance) {
		log.debug("merging Genre instance");
		try {
			Genre result = (Genre) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Genre instance) {
		log.debug("attaching dirty Genre instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Genre instance) {
		log.debug("attaching clean Genre instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}