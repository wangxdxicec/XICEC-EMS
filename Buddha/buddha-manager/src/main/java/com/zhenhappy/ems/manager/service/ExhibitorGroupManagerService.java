package com.zhenhappy.ems.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhenhappy.ems.dao.ExhibitorDao;
import com.zhenhappy.ems.dao.ExhibitorGroupDao;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TExhibitorGroup;
import com.zhenhappy.ems.manager.dto.AddExhibitorGroupRequest;
import com.zhenhappy.ems.manager.dto.ModifyExhibitorGroupRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorGroupRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorGroupResponse;
import com.zhenhappy.ems.manager.exception.DuplicateUsernameException;
import com.zhenhappy.util.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wujianbin on 2014-07-03.
 */
@Service
public class ExhibitorGroupManagerService {
	@Autowired
	private ExhibitorDao exhibitorDao;
	@Autowired
	private ExhibitorGroupDao exhibitorGroupDao;
	@Autowired
    private ExhibitorManagerService exhibitorManagerService;
	@Autowired
    private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
	/**
	 * 分页查询展团
	 * @param request
	 * @return
	 */
	@Transactional
	public QueryExhibitorGroupResponse queryExhibitorGroupByPage(QueryExhibitorGroupRequest request) {
	    Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<TExhibitorGroup> groups = exhibitorGroupDao.queryPageByHQL("select count(*) from TExhibitorGroup", "from TExhibitorGroup", new Object[]{}, page);
        QueryExhibitorGroupResponse response = new QueryExhibitorGroupResponse();
        response.setResultCode(0);
        response.setRows(groups);
        response.setTotal(page.getTotalCount());
        return response;
	}
	
	/**
	 * 查询所有展团
	 * @return
	 */
	@Transactional
	public List<TExhibitorGroup> loadAllExhibitorGroups() {
	    List<TExhibitorGroup> groups = exhibitorGroupDao.queryByHql("from TExhibitorGroup", new Object[]{});
	    return groups.size() > 0 ? groups : null;
	}
	
	/**
	 * 通过id查询展团
	 * @param id
	 * @return
	 */
	@Transactional
	public TExhibitorGroup loadExhibitorGroupById(Integer id){
		TExhibitorGroup group = exhibitorGroupDao.query(id);
		return group;
	}
	
	/**
	 * 通过展团中文名查询展团
	 * @param name
	 * @return
	 */
	@Transactional
	public TExhibitorGroup loadExhibitorGroupByName(String name){
		if(StringUtils.isNotEmpty(name)){
			List<TExhibitorGroup> groups = exhibitorGroupDao.queryByHql("from TExhibitorGroup where groupName=?", new Object[]{ name });
			return groups.size() > 0 ? groups.get(0) : null;
		}else return null;
	}
	
	/**
	 * 通过展团中文名查询展团
	 * @param name
	 * @param exceptId 排除指定的id
	 * @return
	 */
    @Transactional
    public TExhibitorGroup loadExhibitorGroupByName(String name, Integer exceptId) {
    	if(StringUtils.isNotEmpty(name)){
			List<TExhibitorGroup> groups = new ArrayList<TExhibitorGroup>();
			if(exceptId != null){
				groups = getHibernateTemplate().find("from TExhibitorGroup where groupName = ? and id <> ?", new Object[]{name, exceptId.intValue()});
			}else{
				groups = getHibernateTemplate().find("from TExhibitorGroup where groupName = ?", new Object[]{name});
			}
    		return groups.size() > 0 ? groups.get(0) : null;
    	}else return null;
    }
	
	/**
	 * 通过展团英文名查询展团
	 * @param name
	 * @return
	 */
	@Transactional
	public TExhibitorGroup loadExhibitorGroupByNameEn(String nameEn){
		if(StringUtils.isNotEmpty(nameEn)){
			List<TExhibitorGroup> groups = exhibitorGroupDao.queryByHql("from TExhibitorGroup where groupNameEn=?", new Object[]{ nameEn });
			return groups.size() > 0 ? groups.get(0) : null;
    	}else return null;
	}

	/**
	 * 通过展团中文名查询展团
	 * @param nameEn
	 * @param exceptId 排除指定的id
	 * @return
	 */
    @Transactional
    public TExhibitorGroup loadExhibitorGroupByNameEn(String nameEn, Integer exceptId) {
    	if(StringUtils.isNotEmpty(nameEn)){
			List<TExhibitorGroup> groups = new ArrayList<TExhibitorGroup>();
			if(exceptId != null){
				groups = getHibernateTemplate().find("from TExhibitorGroup where groupNameEn = ? and id <> ?", new Object[]{nameEn, exceptId.intValue()});
			}else{
				groups = getHibernateTemplate().find("from TExhibitorGroup where groupNameEn = ?", new Object[]{nameEn});
			}
    		return groups.size() > 0 ? groups.get(0) : null;
    	}else return null;
    }
    
	/**
     * 添加展团账号
     * @param request
     * @param adminId
     * @throws Exception
     */
    @Transactional
    public void addExhibitorGroup(AddExhibitorGroupRequest request, Integer adminId) throws Exception {
    	if(StringUtils.isEmpty(request.getGroupName().trim()) && StringUtils.isEmpty(request.getGroupNameEn().trim())){
	   		 throw new DuplicateUsernameException("展团中英文名至少要有一项");
	   	}
    	if(loadExhibitorGroupByName(request.getGroupName().trim()) != null) throw new DuplicateUsernameException("展团中文名重复");
    	if(loadExhibitorGroupByNameEn(request.getGroupNameEn().trim()) != null) throw new DuplicateUsernameException("展团英文名重复");
        TExhibitorGroup group = new TExhibitorGroup();
        group.setGroupName(request.getGroupName().trim());
        group.setGroupNameEn(request.getGroupNameEn().trim());
        group.setCreateTime(new Date());
        group.setCreateUser(adminId);
    	getHibernateTemplate().save(group);
    }
    
    /**
     * 修改展团账号
     * @param request
     * @param adminId
     * @throws Exception
     */
    @Transactional
    public void modifyExhibitorGroup(ModifyExhibitorGroupRequest request, Integer adminId) throws Exception {
    	if(StringUtils.isEmpty(request.getGroupName().trim()) && StringUtils.isEmpty(request.getGroupNameEn().trim())){
	   		 throw new DuplicateUsernameException("展团中英文名至少要有一项");
	   	}
	   	if(loadExhibitorGroupByName(request.getGroupName().trim(), request.getId()) != null) throw new DuplicateUsernameException("展团中文名重复");
	   	if(loadExhibitorGroupByNameEn(request.getGroupNameEn().trim(), request.getId()) != null) throw new DuplicateUsernameException("展团英文名重复");
	   	TExhibitorGroup group = exhibitorGroupDao.query(request.getId());
    	if(group != null){
    		group.setGroupName(request.getGroupName().trim());
            group.setGroupNameEn(request.getGroupNameEn().trim());
            group.setUpdateTime(new Date());
            group.setUpdateUser(adminId);
            getHibernateTemplate().update(group);
    	}
    }
    
    /**
     * 删除展团
     * @param ids
     */
    @Transactional
    public void deleteExhibitorGroups(Integer[] ids) {
    	List<TExhibitorGroup> groups = exhibitorGroupDao.loadExhibitorGroupsByIds(ids);
    	if(groups != null){
	    	for(TExhibitorGroup group:groups){
	    		List<TExhibitor> exhibitors = exhibitorManagerService.loadExhibitorByGroupId(group.getId());
	    		if(exhibitors != null){
	    			for(TExhibitor exhibitor:exhibitors){
	    				exhibitor.setGroup(null);
	    				exhibitorDao.update(exhibitor);
		    		}
	    		}
	    		getHibernateTemplate().delete(group);
	    	}
    	}
    }
    
}
