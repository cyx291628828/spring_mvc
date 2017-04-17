package com.source.plan.basedao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	private Class clazzP;
	
	public BaseDaoImpl(){  
        //Ŀ�ģ��õ�ʵ�����Ͳ���  
        //�õ���ǰ���ж���  
        Class clazz = this.getClass();  
        //�õ���ǰ������Ĳ���������,һ��ʹ��type�ӽӿ�ParameterizedType  
        Type type = clazz.getGenericSuperclass();  
        ParameterizedType ptype=(ParameterizedType)type;  
        //�õ�ʵ�����Ͳ���  
        Type[] types = ptype.getActualTypeArguments();  
        Class clazzParameter=(Class)types[0];  
        this.clazzP=clazzParameter;  
    }
	/**
	 * ��������
	 */
	public void add(T t) {
		this.getHibernateTemplate().save(t); 
	}
	/**
	 * ɾ������
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);  
	}
	/**
	 * ��������
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);  
	}
	/**
	 * ͨ��id��ѯ����
	 */
	public T findById(Serializable id) {
		return (T)this.getHibernateTemplate().get(clazzP, id);  
	}
	/**
	 * ��ѯ����
	 */
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazzP.getSimpleName());  
	}

}
