package com.zhenhappy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.ExhibitItemRequest;
import com.zhenhappy.dto.ExhibitItemResponse;
import com.zhenhappy.dto.ExhibitItemResponse.Item;
import com.zhenhappy.entity.TExhibitItem;
import com.zhenhappy.service.ExhibitItemService;
import com.zhenhappy.util.LogUtil;

/**
 * 预登记
 * 
 * @author rocsky
 * 
 */
@Controller
@RequestMapping(value = "/client/user/")
public class ExhibitItemController extends BaseController {

	public ExhibitItemController() {
		super(ExhibitItemController.class);
	}

	@Autowired
	private ExhibitItemService exhibitItemService;
	
	@RequestMapping(value = "allExhibitItems", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse list(@RequestBody ExhibitItemRequest request) {
		LogUtil.logRequest(log, this, request);
		ExhibitItemResponse response= new ExhibitItemResponse();
		List<Item> items = new ArrayList<Item>();
		try {
			 List<TExhibitItem> teExhibitItems = exhibitItemService.getTeExhibitItems();
			 if(null !=teExhibitItems){
				 for (TExhibitItem tExhibitItem : teExhibitItems) {
					items.add(new Item(tExhibitItem.getItemId(), tExhibitItem
							.getNameEn(), tExhibitItem.getNameZh(),
							tExhibitItem.getNameTw()));
				}
			 }
			 response.setItems(items);
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("获得展品失败.", e);
		}
		return response;
	}
	
}
