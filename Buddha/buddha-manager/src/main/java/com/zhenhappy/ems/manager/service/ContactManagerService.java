package com.zhenhappy.ems.manager.service;

import java.util.Date;
import java.util.List;

import com.zhenhappy.ems.dao.ContactDao;
import com.zhenhappy.ems.entity.TContact;
import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.manager.dto.QueryContactsRequest;
import com.zhenhappy.ems.manager.dto.QueryContactsResponse;
import com.zhenhappy.ems.service.ContactService;
import com.zhenhappy.util.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Service
public class ContactManagerService extends ContactService {
	@Autowired
    private ContactDao contactDao;
	
	@Autowired
    private JoinerManagerService joinerManagerService;
	
	public QueryContactsResponse queryContacts(QueryContactsRequest request, Integer eid) {
        Page page = new Page();
        page.setPageSize(request.getRows());
        page.setPageIndex(request.getPage());
        List<TContact> contacts = getContactDao().queryPageByHQL("select count(*) from TContact where isDelete!=1 and eid=?", "from TContact where isDelete!=1 and eid=?", new Object[]{eid}, page);
        QueryContactsResponse response = new QueryContactsResponse();
        response.setResultCode(0);
        response.setRows(contacts);
        response.setTotal(page.getTotalCount());
        return response;
	}
	
	/**
	 * 通过id获取联系人
	 * @param id
	 * @return
	 */
	@Transactional
    public TContact loadContactById(Integer id) {
        return contactDao.query(id);
    }
	
	/**
	 * 添加联系人
	 * @param contact
	 */
    @Transactional
    public void addContact(TContact contact) {
    	if(StringUtils.isNotEmpty(contact.getName())){
    		getHibernateTemplate().save(contact);
    	}
    }
    
    /**
	 * 修改联系人
	 * @param contact
	 */
    @Transactional
    public void modifyContact(TContact contact) {
        getHibernateTemplate().update(contact);
    }
    
    /**
     * 移除联系人
     * @param tids
     */
    @Transactional
    public void removeContactByCIds(Integer[] cids) {
    	List<TContact> contacts = contactDao.loadContactByCids(cids);
    	if(contacts != null){
	    	for(TContact contact:contacts){
	    		contact.setIsDelete(1);
	    		getHibernateTemplate().update(contact);
	    	}
    	}
    }
    
    /**
     * 添加到参展人员
     * @param cids
     * @param adminId
     */
    @Transactional
    public void addToJoiners(Integer[] cids,Integer adminId) {
    	List<TContact> contacts = contactDao.loadContactByCids(cids);
    	if(contacts != null){
	    	for(TContact contact:contacts){
	    		List<TExhibitorJoiner> joiners = joinerManagerService.loadExhibitorJoinerByName(contact.getName(),contact.getEid());
	    		if(joiners.size() == 0){
	    			TExhibitorJoiner joiner = new TExhibitorJoiner(contact.getEid(),contact.getName(),
	    														   contact.getPosition(),contact.getPhone(),
	    														   contact.getEmail(),new Date(),adminId,null,0);
	    			joinerManagerService.addExhibitorJoiner(joiner);
	    		}else{
	    			for(TExhibitorJoiner joiner:joiners){
	    				joiner.setIsDelete(0);
	    				joinerManagerService.modifyExhibitorJoiner(joiner);
	    			}
	    		}
	    	}
    	}
    }

	/**
     * 删除联系人
     * @param tids
     */
    @Transactional
    public void deleteContactsByEids(Integer[] eids) {
    	List<TContact> contacts = contactDao.loadContactByEids(eids);
    	if(contacts.size() > 0){
	    	for(TContact contact:contacts){
	    		getHibernateTemplate().delete(contact);
	    	}
    	}
    }
}
