package com.zhenhappy.controller;

import java.util.List;
import java.util.Map;

import com.zhenhappy.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.service.ProductTypeService;
import com.zhenhappy.util.LogUtil;

@Controller
@RequestMapping(value = "/client/user/")
public class ProductController extends BaseController {

	public ProductController() {
		super(ProductController.class);
	}

	@Autowired
	private ProductTypeService typeService;

	@RequestMapping(value = "allProductTypes", method = RequestMethod.POST)
	@ResponseBody
	public ProductTypeResponse loadAllProductTypes(@RequestBody AfterLoginRequest request) {
		LogUtil.logRequest(log, this, request);
		ProductTypeResponse response = new ProductTypeResponse();
		try {
			List<ProductType> types = typeService.loadAllProductTypes();
			response.setProductTypes(types);
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("获得产品类型失败.",e);
		}
		return response;
	}
	
//	@RequestMapping(value = "companyProducts", method = RequestMethod.POST)
//	@ResponseBody
//	public ProductTypeResponse loadCompanyProductTypes(@RequestBody AfterLoginRequest request){
//		LogUtil.logRequest(log, this, request);
//		ProductTypeResponse response = new ProductTypeResponse();
//		try {
//			List<ProductTypeDTO> types = typeService.loadCompanyProductTypes(request.getUserId());
//			response.setProductTypes(types);
//		} catch (Exception e) {
//			response.setErrorCode(ErrorCode.ERROR);
//			log.error("获得产品类型失败.",e);
//		}
//		return response;
//	}
	
	@RequestMapping(value="countCompanies",method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse countCompaniesInType(@RequestBody CountCompanyInTypeRequest request){
		LogUtil.logRequest(log, this, request);
		CountCompanyInTypeResponse response = new CountCompanyInTypeResponse();
		try{
			Map<String, Integer> typeCode_count = typeService.countCompanyUnderType(request.getTypeCode());
			response.setType_count(typeCode_count);
		}catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
}
