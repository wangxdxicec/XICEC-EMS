package com.zhenhappy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.CommentsDao;
import com.zhenhappy.entity.TComments;

/**
 * 
 * @author rocsky
 *
 */
@Service
public class CommentsService {

	@Autowired
	private CommentsDao commentsDao;
	
	@Transactional
	public void save(TComments request){
		commentsDao.saveOrUpdate(request);
	}
}
