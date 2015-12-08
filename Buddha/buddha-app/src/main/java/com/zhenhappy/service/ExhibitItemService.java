package com.zhenhappy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhenhappy.dao.ExhibitItemDao;
import com.zhenhappy.entity.TExhibitItem;

/**
 * 
 * @author rocsky
 *
 */
@Service
public class ExhibitItemService {
	
	@Autowired
	private ExhibitItemDao exhibitItemDao;
	public List<TExhibitItem> getTeExhibitItems(){
		return exhibitItemDao.query();
	}
}
