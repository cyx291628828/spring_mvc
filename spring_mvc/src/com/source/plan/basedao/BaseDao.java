package com.source.plan.basedao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * �������
	 * @param t
	 */
    public void add(T t);  
    /**
     * ɾ������
     * @param t
     */
    public void delete(T t);  
    /**
     * ��������
     * @param t
     */
    public void update(T t);  
    /**
     * ����id��ѯ
     * @param id
     * @return
     */
    public T findById(Serializable id);  
    /**
     * ��ѯ����
     * @return
     */
    public List<T> findAll();  
}
