/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2013 ICSS All Rights Reserved.
 * [FileName]
 * NBaseDao.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2013-2-7 Administrator 最初版本
 */
package com.zhenhappy.ems.dao;



import java.io.Serializable;
import java.util.List;

import com.zhenhappy.util.Page;

/**
 * <b>Summary: </b> TODO 数据库操作基类
 */
public interface BaseDao<T> {
	/**
	 * 创建
	 * 
	 * @param t
	 */
	public void create(T t);

	/**
	 * 
	 * <b>Summary: </b> createOrUpdate：更新或创建
	 * 
	 * @param t
	 */
	public void createOrUpdate(T t);

	/**
	 * 
	 * <b>Summary: </b> batchSave：批量更新
	 * 
	 * @param objects
	 */
	public void batchSave(List<T> objects);

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(T t) throws Exception;

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 查询-通过主键ID查询
	 * 
	 * @param id
	 * @return
	 */
	public T query(Serializable id);

	/**
	 * 查询-查询全部
	 * 
	 * @return
	 */
	public List<T> query();

	/**
	 * 
	 * <b>Summary: </b> queryOrderBy 查询全部,带排序
	 * 
	 * @param orderColumn
	 * @param orderType
	 * @return
	 */
	public List<T> queryOrderBy(String orderColumn, String orderType);

	/**
	 * 
	 * <b>Summary: </b> update sql语句更新
	 * 
	 * @param update
	 * @param objects
	 * @param queryMethod
	 * @return
	 */
	public int update(String update, Object[] objects, String queryMethod);

	/**
	 * 按参数查询
	 * 
	 * @return 查询结果
	 */
	public List<T> queryByHql(String select, Object[] values);

	/**
	 * 使用本地sql语句
	 */
	public List<T> queryBySql(String select, Object[] objects, Class entity);

	/**
	 * 
	 * <b>Summary: </b> queryForList 分页查找数据
	 * 
	 * @param selectCount
	 * @param select
	 * @param values
	 * @param page
	 * @return
	 */
	public List queryPageByHQL(final String selectCount, final String select, final Object[] values, final Page page);

	/**
	 * 
	 * <b>Summary: </b> queryForList 分页查找数据,可自动映射实体
	 * 
	 * @param selectCount
	 * @param select
	 * @param values
	 * @param page
	 * @param targetClass
	 * @return
	 */
	public List queryPageBySQL(final String selectCount, final String select, final Object[] values, final Page page,
                               Class targetClass);

	public List queryPageByJDBCTemplate(final String selectCount, final String select, final Object[] values, Page page);
	
	/**
	 * 
	 * <b>Summary: </b> queryForObject 获得unique result
	 * 
	 * @param select
	 * @param values
	 * @param queryType
	 * @return
	 */
	public Object queryForObject(String select, Object[] values, String queryType);

	/**
	 * 
	 * <b>Summary: </b> queryCount 查找数量
	 * 
	 * @param select
	 * @param values
	 * @param queryType
	 * @return
	 */
	public int queryCount(final String select, final Object[] values, String queryType);
}
