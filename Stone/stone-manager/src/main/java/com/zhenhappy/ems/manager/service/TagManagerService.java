package com.zhenhappy.ems.manager.service;

import java.util.List;

import com.zhenhappy.ems.dao.ExhibitorDao;
import com.zhenhappy.ems.dao.TagDao;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TProduct;
import com.zhenhappy.ems.entity.TTag;
import com.zhenhappy.ems.manager.dto.QueryProductsResponse;
import com.zhenhappy.ems.manager.dto.QueryTagRequest;
import com.zhenhappy.ems.manager.dto.QueryTagResponse;
import com.zhenhappy.ems.manager.exception.DuplicateTagException;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wujianbin on 2014-07-03.
 */
@Service
public class TagManagerService {
	@Autowired
	private TagDao tagDao;
	@Autowired
    private ExhibitorDao exhibitorDao;
	@Autowired
    private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
	/**
	 * 分页查询所属人
	 * @param request
	 * @return
	 */
	@Transactional
	public QueryTagResponse loadTagsByPage(QueryTagRequest request) {
	    Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<TTag> tags = tagDao.queryPageByHQL("select count(*) from TTag", "from TTag", new Object[]{}, page);
        QueryTagResponse response = new QueryTagResponse();
        response.setResultCode(0);
        response.setRows(tags);
        response.setTotal(page.getTotalCount());
        return response;
	}
	
	/**
	 * 查询所有所属人
	 * @return
	 */
	@Transactional
	public List<TTag> loadAllTags() {
	    List<TTag> tags = null;
	    tags = tagDao.queryByHql("from TTag", new Object[]{});
	    return tags;
	}
	
	/**
	 * 通过id查询所属人
	 * @param id
	 * @return
	 */
	@Transactional
	public TTag loadTagById(Integer id){
		TTag tag = tagDao.query(id);
		return tag;
	}
	
	/**
	 * 通过名字查询所属人
	 * @param name
	 * @return
	 */
	@Transactional
	public TTag loadTagByName(String name){
		List<TTag> tags = tagDao.queryByHql("from TTag where name=?", new Object[]{ name });
		return tags.size()>0?tags.get(0):null;
	}

	/**
     * 添加所属人
     * @param tag
     * @throws DuplicateTagException
     */
    @Transactional
    public void addTag(TTag tag) {
        getHibernateTemplate().save(tag);
    }
    
    /**
	 * 修改所属人
	 * @param tag
	 */
    @Transactional
    public void modifyTag(TTag tag) {
        getHibernateTemplate().update(tag);
    }
    
    /**
     * 删除所属人
     * @param tids
     */
    @Transactional
    public void removeTagsByTids(Integer[] tids) {
    	List<TTag> tags = tagDao.loadTagsByTids(tids);
    	if(tags != null){
	    	for(TTag tag:tags){
	    		List<TExhibitor> exhibitors = queryExhibitorByTag(tag.getId());
	    		if(exhibitors != null){
	    			for(TExhibitor exhibitor:exhibitors){
	    				exhibitor.setTag(null);
	    				exhibitorDao.update(exhibitor);
		    		}
	    		}
	    		getHibernateTemplate().delete(tag);
	    	}
    	}
    }
    
    /**
     * 通过所属人查询展商
     * @param tid
     */
    @Transactional
    public List<TExhibitor> queryExhibitorByTag(Integer tid) {
    	List<TExhibitor> exhibitors = exhibitorDao.queryByHql("from TExhibitor where tag=?", new Object[]{tid});
    	return exhibitors;
    }
}
