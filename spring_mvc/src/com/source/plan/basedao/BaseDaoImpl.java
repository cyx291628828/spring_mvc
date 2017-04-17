package com.source.plan.basedao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	private Class clazzP;
	
	public BaseDaoImpl(){  
        //目的：得到实际类型参数  
        //得到当前运行对象  
        Class clazz = this.getClass();  
        //得到当前对象父类的参数化类型,一般使用type子接口ParameterizedType  
        Type type = clazz.getGenericSuperclass();  
        ParameterizedType ptype=(ParameterizedType)type;  
        //得到实际类型参数  
        Type[] types = ptype.getActualTypeArguments();  
        Class clazzParameter=(Class)types[0];  
        this.clazzP=clazzParameter;  
    }
	/**
	 * 保存数据
	 */
	public void add(T t) {
		this.getHibernateTemplate().save(t); 
	}
	/**
	 * 删除数据
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);  
	}
	/**
	 * 更新数据
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);  
	}
	/**
	 * 通过id查询数据
	 */
	public T findById(Serializable id) {
		return (T)this.getHibernateTemplate().get(clazzP, id);  
	}
	/**
	 * 查询所有
	 */
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazzP.getSimpleName());  
	}

}
