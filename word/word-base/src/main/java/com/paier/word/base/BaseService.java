package com.paier.word.base;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 */

public interface BaseService<T> {

	public List<T> getList(T entity);
	
	public PageInfo<T> getListByPage(T entity);
	
	public T getByPrimaryKey(Long id);
	
	public Integer updateById(T entity);
	
	public Long insertWithParams(T entity);
	
	public int deleteById(Long id);
	
}
