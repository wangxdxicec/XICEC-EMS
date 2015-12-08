package com.zhenhappy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.ShowItemDao;
import com.zhenhappy.entity.TShowItem;
import com.zhenhappy.util.QueryFactory;

/**
 * 
 * @author rocsky
 *
 */
@Service
public class ShowItemService {

	@Autowired
	private ShowItemDao showItemDao;
	public List<TShowItem> list(Integer showId){
		return showItemDao.queryBySql("Select * from t_show_item where show_id=?", new Integer[]{showId}, TShowItem.class);
	}
	@Transactional
	public void saveOrUpdate(List<TShowItem> tShowItems){
		showItemDao.update("delete from t_show_item where show_id =?", new Integer[]{tShowItems.get(0).getShowId()}, QueryFactory.SQL);
		for (TShowItem tShowItem : tShowItems) {
			showItemDao.saveOrUpdate(tShowItem);
		}
	}
	
}
