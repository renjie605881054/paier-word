package com.paier.word.base;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 基础类
 * hj
 * @version 1.0
 * @date 2017年5月26日
 * Copyright 杭州复广科技有限公司 All Rights Reserved
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public abstract class BaseLogic<T> extends RedisService {
	/**
	 * 根据实体查找列表
	 * @param entity
	 * @return
	 */
	public abstract List<T> getList(T entity);
	
	/**
	 * 根据实体查询分页列表
	 * @param entity
	 * @return
	 */
	public abstract PageInfo<T> getListByPage(T entity);
	
	/**
	 * 根据id查询信息
	 * @param id
	 * @return
	 */
	public abstract T getByPrimaryKey(Long id);

	/**
	 * 更新信息
	 * @param entity
	 * @return
	 */
	public abstract int updateById(T entity);
	
	/**
	 * 保存信息
	 * @param entity
	 * @return
	 */
	public abstract Long insertWithParams(T entity);
	
	/**
	 * 根据id删除信息
	 * @param id
	 * @return
	 */
	public abstract int deleteById(Long id);
	
}
