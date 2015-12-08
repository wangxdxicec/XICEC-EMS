package com.zhenhappy.ems.manager.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.zhenhappy.ems.dao.ArticleDao;
import com.zhenhappy.ems.entity.TArticle;
import com.zhenhappy.ems.entity.TProduct;
import com.zhenhappy.ems.manager.dto.QueryArticleRequest;
import com.zhenhappy.ems.manager.dto.QueryArticleResponse;
import com.zhenhappy.ems.service.ArticleService;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.util.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wujianbin on 2014-07-03.
 */
@Service
public class ArticleManagerService extends ExhibitorService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleService articleService;
	 
	@Transactional
	public QueryArticleResponse loadArticlesByPage(QueryArticleRequest request) throws UnsupportedEncodingException {
		List<String> conditions = new ArrayList<String>();
        if (StringUtils.isNotEmpty(request.getTitle())) {
            conditions.add(" (title like '%" + new String(request.getTitle().getBytes("ISO-8859-1"),"GBK")  + "%' OR title like '%" + new String(request.getTitle().getBytes("ISO-8859-1"),"utf-8")  + "%') ");
        }
        if (StringUtils.isNotEmpty(request.getDigest())) {
            conditions.add(" (digest like '%" + new String(request.getDigest().getBytes("ISO-8859-1"),"GBK") + "%' OR digest like '%" + new String(request.getDigest().getBytes("ISO-8859-1"),"utf-8") + "%') ");
        }
        if (StringUtils.isNotEmpty(request.getContent())) {
            conditions.add(" (content like '%" + new String(request.getContent().getBytes("ISO-8859-1"),"GBK") + "%' OR content like '%" + new String(request.getContent().getBytes("ISO-8859-1"),"utf-8") + "%') ");
        }
        if (StringUtils.isNotEmpty(request.getTitleEn())) {
            conditions.add(" (titleEn like '%" + new String(request.getTitleEn().getBytes("ISO-8859-1"),"GBK") + "%' OR titleEn like '%" + new String(request.getTitleEn().getBytes("ISO-8859-1"),"utf-8") + "%') ");
        }
        if (StringUtils.isNotEmpty(request.getDigestEn())) {
            conditions.add(" (digestEn like '%" + new String(request.getDigestEn().getBytes("ISO-8859-1"),"GBK") + "%' OR digestEn like '%" + new String(request.getDigestEn().getBytes("ISO-8859-1"),"utf-8") + "%') ");
        }
        if (StringUtils.isNotEmpty(request.getContentEn())) {
            conditions.add(" (contentEn like '%" + new String(request.getContentEn().getBytes("ISO-8859-1"),"GBK") + "%' OR contentEn like '%" + new String(request.getContentEn().getBytes("ISO-8859-1"),"utf-8") + "%') ");
        }
        String conditionsSql = StringUtils.join(conditions, " and ");
        if(conditionsSql != null && !"".equals(conditionsSql)){
        	conditionsSql = " where " + conditionsSql;
        }
	    Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<TArticle> articles = articleDao.queryPageByHQL("select count(*) from TArticle" + conditionsSql, "from TArticle" + conditionsSql + " order by createTime ASC", new Object[]{}, page);
        QueryArticleResponse response = new QueryArticleResponse();
        response.setResultCode(0);
        response.setRows(articles);
        response.setTotal(page.getTotalCount());
        return response;
	}
	
	/**
     * 删除文章
     * @param ids
     */
    @Transactional
    public void deleteArticlesByIds(Integer[] ids){
    	List<TArticle> articles = articleDao.loadArticlesByIds(ids);
    	for(TArticle article:articles){
    		if(article != null) articleService.remove(article);
    	}
    }
}
