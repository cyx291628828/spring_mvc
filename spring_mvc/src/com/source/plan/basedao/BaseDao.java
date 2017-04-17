package com.source.plan.basedao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 添加数据
	 * @param t
	 */
    public void add(T t);  
    /**
     * 删除数据
     * @param t
     */
    public void delete(T t);  
    /**
     * 更新数据
     * @param t
     */
    public void update(T t);  
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T findById(Serializable id);  
    /**
     * 查询所有
     * @return
     */
    public List<T> findAll();  
}
