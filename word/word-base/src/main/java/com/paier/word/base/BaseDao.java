package com.paier.word.base;

import java.util.List;

/**
 * dao层基类
 */
public interface BaseDao<T> {
	/**
	 * 根据实体查找列表
	 * @param entity
	 * @return
	 */
	List<T> getList(T entity);
	
	/**
	 * 根据实体查找列表(含分页)
	 * @param entity
	 * @return
	 */
	List<T> getListByPage(T entity);
	
	/**
	 * 根据id删除信息
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 插入信息
	 * @param record
	 * @return
	 */
    int insert(T record);

    /**
     * 插入信息(如果字段不为空插入)
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据id查询实体信息
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 更新信息(如果字段不为空更新)
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 更新信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
    /**
     * 导出Excel表格
     * @param entity
     * @return
     */
    List<Object> getExcelList(T entity);
	
}
