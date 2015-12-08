/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2013 ICSS All Rights Reserved.
 * [FileName]
 * NBaseDaoHibernateImp.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2013-2-7 Administrator 最初版本
 */
package com.zhenhappy.ems.dao.imp;

import com.zhenhappy.ems.dao.BaseDao;
import com.zhenhappy.util.Page;
import com.zhenhappy.util.QueryFactory;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

/**
 * <b>Summary: </b> TODO
 */
public class BaseDaoHibernateImp<T> extends HibernateDaoSupport implements BaseDao<T> {
	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
    @Autowired
    protected HibernateTemplate hibernateTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private final Class<T> clazz;

    /**
     * <b>Summary: </b> 构造一个 NBaseDaoHibernateImp <b>Remarks: </b> 构造类
     * NBaseDaoHibernateImp 的构造函数 NBaseDaoHibernateImp
     *
     */

    public BaseDaoHibernateImp() {
        super();
        clazz = getGenericType(0);
    }

    public Class getGenericType(int index) {
        Type genType = getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * <b>Summary: </b> 复写方法 create
     *
     * @param t
     */
    @Override
    public void create(T t) {
        hibernateTemplate.save(t);
    }

    /**
     * <b>Summary: </b> 复写方法 delete
     *
     * @param t
     * @throws Exception
     */
    @Override
    public void delete(T t) throws Exception {
        hibernateTemplate.delete(t);
    }

    /**
     * <b>Summary: </b> 复写方法 update
     *
     * @param t
     */
    @Override
    public void update(T t) {
        hibernateTemplate.update(t);
    }

    /**
     * <b>Summary: </b> 复写方法 query
     *
     * @param id
     * @return
     */
    @Override
    public T query(Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    /**
     * <b>Summary: </b> 复写方法 query
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> query() {
        StringBuffer buffer = new StringBuffer(" from ").append(clazz.getSimpleName());
        return hibernateTemplate.find(buffer.toString());
    }

    /**
     * <b>Summary: </b> 复写方法 queryOrderBy
     *
     * @param orderColumn
     * @param orderType   default type is asc
     * @return String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> queryOrderBy(String orderColumn, String orderType) {
        StringBuffer buffer = new StringBuffer(" from ").append(clazz.getSimpleName());
        if (StringUtils.isNotEmpty(orderColumn)) {
            buffer.append(" order by ").append(orderColumn).append(" ").append(orderType == null ? "asc" : orderType);
        }
        return hibernateTemplate.find(buffer.toString());
    }

    /**
     * <b>Summary: </b> 复写方法 update
     *
     * @param update
     * @param objects
     * @param queryMethod Object[], String)
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public int update(final String update, final Object[] objects, final String queryMethod) {
        return ((Integer) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = QueryFactory.getQuery(session, update, queryMethod, null);
                if (objects != null) {
                    for (int i = 0; i < objects.length; i++) {
                        query.setParameter(i, objects[i]);
                    }
                }
                return query.executeUpdate();
            }
        })).intValue();
    }

    /**
     * <b>Summary: </b> 复写方法 queryByHql
     *
     * @param select Object[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> queryByHql(String select, Object[] values) {
        return hibernateTemplate.find(select, values);
    }

    /**
     * <b>Summary: </b> 复写方法 queryBySql
     *
     * @param select
     * @param objects
     * @param entity
     * @return Object[], Class)
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List<T> queryBySql(final String select, final Object[] objects, final Class entity) {
        return queryForList(select, objects, entity, QueryFactory.SQL);
    }

    /**
     * <b>Summary: </b> 复写方法 queryForList
     *
     * @param selectCount
     * @param select
     * @param values
     * @param page
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List queryPageByHQL(String selectCount, final String select, final Object[] values, final Page page) {
        page.setTotalCount(queryCount(selectCount, values, QueryFactory.HQL));
        if (page.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            return queryForListPage(select, values, null, QueryFactory.HQL, page);
        }
    }

    /**
     * <b>Summary: </b> 复写方法 queryForList
     *
     * @param selectCount
     * @param select
     * @param values
     * @param page
     * @param targetClass
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List queryPageBySQL(String selectCount, final String select, final Object[] values, final Page page,
                               final Class targetClass) {
        page.setTotalCount(queryCount(selectCount, values, QueryFactory.SQL));
        if (page.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            return queryForListPage(select, values, targetClass, QueryFactory.SQL, page);
        }
    }

    /**
     * <b>Summary: </b> 复写方法 queryForObject
     *
     * @param select
     * @param values
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Object queryForObject(final String select, final Object[] values, final String queryType) {
        HibernateCallback selectCallback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
                Query query = QueryFactory.getQuery(session, select, queryType, null);
                if (values != null) {
                    for (int i = 0; i < values.length; i++)
                        query.setParameter(i, values[i]);
                }
                return query.uniqueResult();
            }
        };
        return hibernateTemplate.execute(selectCallback);
    }

    /**
     * <b>Summary: </b> queryCount 用于查找总条数
     *
     * @param select
     * @param values
     * @return
     */
    public int queryCount(final String select, final Object[] values, String queryType) {
        Object object = queryForObject(select, values, queryType);
        int count = 0;
        if (object instanceof BigInteger) {
            count = ((BigInteger) object).intValue();
        } else if (object instanceof Integer) {
            count = ((Integer) object).intValue();
        } else {
            count = ((Long) object).intValue();
        }
        return count;
    }

    @SuppressWarnings("rawtypes")
    private List queryForList(final String select, final Object[] objects, final Class entity, final String queryType) {
        HibernateCallback selectCallback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
                StringBuffer condition_buffer = new StringBuffer();
                condition_buffer.append(select);
                Query query = QueryFactory.getQuery(session, condition_buffer.toString(), queryType, entity);
                if (objects != null) {
                    for (int i = 0; i < objects.length; i++)
                        query.setParameter(i, objects[i]);
                }
                return query.list();
            }
        };
        return (List) hibernateTemplate.executeFind(selectCallback);
    }

    @SuppressWarnings("rawtypes")
    private List queryForListPage(final String select, final Object[] values, final Class entity,
                                  final String queryType, final Page page) {
        HibernateCallback selectCallback = new HibernateCallback() {
            public Object doInHibernate(Session session) {
                StringBuffer condition_buffer = new StringBuffer();
                condition_buffer.append(select);
                Query query = QueryFactory.getQuery(session, condition_buffer.toString(), queryType, entity);
                if (values != null) {
                    for (int i = 0; i < values.length; i++)
                        query.setParameter(i, values[i]);
                }
                return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list();
            }
        };
        return (List) hibernateTemplate.executeFind(selectCallback);
    }

    /**
     * <b>Summary: </b> 复写方法 batchSave
     *
     * @param objects
     */
    @Override
    public void batchSave(List<T> objects) {
        for (int i = 0; i < objects.size(); i++) {
            hibernateTemplate.save(objects.get(i));
            if (i % 9 == 0) {
                hibernateTemplate.flush();
            }
        }
        hibernateTemplate.flush();
    }

    /**
     * <b>Summary: </b> 复写方法 createOrUpdate
     *
     * @param t
     */
    @Override
    public void createOrUpdate(T t) {
        hibernateTemplate.saveOrUpdate(t);
    }

    @Override
    public List queryPageByJDBCTemplate(String selectCount, String select, Object[] values, Page page) {
        int count = jdbcTemplate.queryForInt(selectCount, values);
        page.setTotalCount(count);
        return jdbcTemplate.queryForList(select, values);
    }

}
