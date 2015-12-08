package com.zhenhappy.util;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public abstract class QueryFactory {
	public final static String SQL = "sql";
	public final static String SQL_WITH_CACHE = "sqlWithCache";
	public final static String HQL = "hql";
	public final static String HQL_WITH_CACHE = "hqlWithCache";

	@SuppressWarnings("rawtypes")
	public static Query getQuery(Session session, String select, String method, Class entity) {
		if (SQL.equals(method)) {
			SQLQuery sqlQuery = session.createSQLQuery(select);
			if (entity != null) {
				sqlQuery.addEntity(entity);
			}
			return sqlQuery;
		} else if (SQL_WITH_CACHE.equals(method)) {
			SQLQuery sqlQuery = session.createSQLQuery(select);
			sqlQuery.setCacheable(true);
			if (entity != null) {
				sqlQuery.addEntity(entity);
			}
			return sqlQuery;
		} else if (HQL.equals(method)) {
			return session.createQuery(select);
		} else if (HQL_WITH_CACHE.equals(method)) {
			return session.createQuery(select).setCacheable(true);
		} else {
			return null;
		}
	}
}
