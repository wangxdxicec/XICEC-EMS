package com.zhenhappy.ems.dao.imp;

import java.util.List;

import com.zhenhappy.ems.dao.ArticleDao;
import com.zhenhappy.ems.entity.TArticle;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wujianbin on 2014-07-02.
 */
@Repository
public class ArticleDaoImp extends BaseDaoHibernateImp<TArticle> implements ArticleDao {

	@Override
	public List<TArticle> loadArticlesByIds(Integer[] ids) {
		Query q = this.getSession().createQuery("select new TArticle(a.id) from TArticle a where a.id in (:ids)");
		q.setParameterList("ids", ids);
		return q.list();
	}
	
}
