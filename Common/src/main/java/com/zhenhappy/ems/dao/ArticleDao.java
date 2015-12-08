package com.zhenhappy.ems.dao;

import java.util.List;

import com.zhenhappy.ems.entity.TArticle;

/**
 * Created by wujianbin on 2014-07-02.
 */
public interface ArticleDao extends BaseDao<TArticle> {

	List<TArticle> loadArticlesByIds(Integer[] ids);
}
