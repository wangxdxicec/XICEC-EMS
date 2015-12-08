package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.dao.JoinerDao;
import com.zhenhappy.ems.dao.ProductDao;
import com.zhenhappy.ems.entity.TContact;
import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.entity.TProduct;
import com.zhenhappy.ems.manager.dto.QueryJoinersRequest;
import com.zhenhappy.ems.manager.dto.QueryJoinersResponse;
import com.zhenhappy.ems.service.JoinerService;
import com.zhenhappy.util.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Service
public class JoinerManagerService extends JoinerService {
	@Autowired
    private JoinerDao joinerDao;
	
    public QueryJoinersResponse queryJoiners(QueryJoinersRequest request,Integer einfoId) {
        List<String> conditions = new ArrayList<String>();
        conditions.add(" eid=" + request.getEid());
        if (StringUtils.isNotEmpty(request.getName())) {
            conditions.add(" name like '%" + request.getName() + "%' ");
        }
        String conditionsSql = StringUtils.join(conditions, " and ");
        Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<TExhibitorJoiner> joiners = getJoinerDao().queryPageByHQL("select count(*) from TExhibitorJoiner where " + conditionsSql, "from TExhibitorJoiner where " + conditionsSql + " and isDelete != 1", new Object[]{}, page);
        for(TExhibitorJoiner j:joiners) {
            j.setName(replaceBlank(j.getName()));
            j.setPosition(replaceBlank(j.getPosition()));
            j.setTelphone(replaceBlank(j.getTelphone()));
            j.setEmail(replaceBlank(j.getEmail()));
        }
        QueryJoinersResponse response = new QueryJoinersResponse();
        response.setResultCode(0);
        response.setRows(joiners);
        response.setTotal(page.getTotalCount());
        return response;
    }

    private String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
	 * 通过id获取参展人员
	 * @param eid
	 * @return
	 */
	@Transactional
    public List<TExhibitorJoiner> loadExhibitorJoinerByEid(Integer eid) {
		List<TExhibitorJoiner> joiners = getJoinerDao().queryByHql("from TExhibitorJoiner where isDelete!=1 and eid=?", new Object[]{eid});
		return joiners;
    }

	/**
	 * 通过id获取参展人员
	 * @param id
	 * @return
	 */
	@Transactional
	public TExhibitorJoiner loadExhibitorJoinerById(Integer id) {
		return joinerDao.query(id);
	}
	
	/**
	 * 通过姓名获取参展人员
	 * @param name, eid
	 * @return
	 */
	@Transactional
    public List<TExhibitorJoiner> loadExhibitorJoinerByName(String name,Integer eid) {
        return joinerDao.queryByHql("from TExhibitorJoiner where name=? and eid=?", new Object[]{name,eid});
    }
    
    /**
     * 添加参展人员
     * @param exhibitorJoiner
     */
    @Transactional
    public void addExhibitorJoiner(TExhibitorJoiner exhibitorJoiner) {
        getHibernateTemplate().save(exhibitorJoiner);
    }
    
    /**
     * 修改参展人员
     * @param exhibitorJoiner
     */
    @Transactional
    public void modifyExhibitorJoiner(TExhibitorJoiner exhibitorJoiner) {
        getHibernateTemplate().update(exhibitorJoiner);
    }
    
    /**
     * 移除参展人员
     * @param jids, adminId
     */
    @Transactional
    public void removeJoinerByJIds(Integer[] jids,Integer adminId) {
    	List<TExhibitorJoiner> joiners = joinerDao.loadJoinersByJIds(jids);
    	for(TExhibitorJoiner joiner:joiners){
    		joiner.setIsDelete(1);
    		joiner.setAdmin(adminId);
    		joiner.setAdminUpdateTime(new Date());
    		getHibernateTemplate().update(joiner);
    	}
    }

    /**
     * 删除参展人员
     * @param eids
     */
	public void deleteJoinersByEids(Integer[] eids) {
		List<TExhibitorJoiner> joiners = joinerDao.loadJoinersByEids(eids);
		if(joiners.size() > 0){
	    	for(TExhibitorJoiner joiner:joiners){
	    		getHibernateTemplate().delete(joiner);
	    	}
		}
	}
}
