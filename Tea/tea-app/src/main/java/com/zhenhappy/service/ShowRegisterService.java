package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhenhappy.dao.ShowRegisterDao;
import com.zhenhappy.entity.TShowItem;
import com.zhenhappy.entity.TShowRegister;

/**
 * 
 * @author rocsky
 *
 */
@Service
public class ShowRegisterService {

	@Autowired
	private ShowRegisterDao registerDao;
	
	@Autowired
	private ShowItemService showItemService;
	
	public TShowRegister getTRegister(Integer showId) {
		return registerDao.query(showId);
	}
	@Transactional
	public void save(TShowRegister request, boolean update) {
		List<TShowItem> showItems = new ArrayList<TShowItem>();
		for(Integer item:request.getItems()){
			showItems.add(new TShowItem(request.getShowId(), item));
		}
		showItemService.saveOrUpdate(showItems);
		if (update) {
			registerDao.update(request);
		} else {
			registerDao.create(request);
		}
	}
}
