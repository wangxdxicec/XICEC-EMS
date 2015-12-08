package com.zhenhappy.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.AfterLoginRequest;
import com.zhenhappy.dto.BaseProduct;
import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.CountCompanyInTypeRequest;
import com.zhenhappy.dto.CountCompanyInTypeResponse;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.ProductRequest;
import com.zhenhappy.dto.ProductResponse;
import com.zhenhappy.dto.ProductTypeDTO;
import com.zhenhappy.dto.ProductTypeResponse;
import com.zhenhappy.dto.ProductsRequest;
import com.zhenhappy.dto.ProductsResponse;
import com.zhenhappy.entity.TProduct;
import com.zhenhappy.service.ProductService;
import com.zhenhappy.service.ProductTypeService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.ApplicationContextUtil;
import com.zhenhappy.util.LogUtil;
import com.zhenhappy.util.Page;

@Controller
@RequestMapping(value = "/client/user/")
public class ProductController extends BaseController {

	public ProductController() {
		super(ProductController.class);
	}

	@Autowired
	private ProductTypeService typeService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "allProducts", method = RequestMethod.POST)
	@ResponseBody
	public ProductsResponse allProducts(@RequestBody ProductsRequest request) {
		LogUtil.logRequest(log, this, request);
		ProductsResponse response = new ProductsResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<TProduct> products = productService.query(page,request.getLocal(),
					request.getProductName(), request.getEid());
			response.setHasNext(page.getHasNext());
			List<BaseProduct> baseProducts = new ArrayList<BaseProduct>();
			for (TProduct product : products) {
				BaseProduct baseProduct = new BaseProduct();
				
				BeanUtils.copyProperties(product, baseProduct);
				baseProducts.add(baseProduct);
			}
			response.setProducts(baseProducts);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
	

	@RequestMapping(value = "getProduct", method = RequestMethod.POST)
	@ResponseBody
	public ProductResponse getProduct(@RequestBody ProductRequest request) {
		LogUtil.logRequest(log, this, request);
		ProductResponse response = new ProductResponse();
		try {
			TProduct product = productService.get(request.getProductId());
			BeanUtils.copyProperties(product, response);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "productImage", method = RequestMethod.GET)
	public void getProductImage(@RequestParam("imageUrl") String imageUrl, HttpServletResponse response) {
		if(StringUtils.isBlank(imageUrl)){
			return ;
		}
		OutputStream stream = null;
		try {
			stream = response.getOutputStream();
			IOUtils.copy(new FileInputStream(ApplicationContextUtil.getBean(SystemConfig.class).getVal("product_img_local_url")+imageUrl), stream);
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != stream){
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "allProductTypes", method = RequestMethod.POST)
	@ResponseBody
	public ProductTypeResponse loadAllProductTypes(@RequestBody AfterLoginRequest request) {
		LogUtil.logRequest(log, this, request);
		ProductTypeResponse response = new ProductTypeResponse();
		try {
			List<ProductTypeDTO> types = typeService.loadProductTypes(request.getUserId());
			response.setProductTypes(types);
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("获得产品类型失败.", e);
		}
		return response;
	}
	
	@RequestMapping(value = "companyProducts", method = RequestMethod.POST)
	@ResponseBody
	public ProductTypeResponse loadCompanyProductTypes(@RequestBody AfterLoginRequest request){
		LogUtil.logRequest(log, this, request);
		ProductTypeResponse response = new ProductTypeResponse();
		try {
			List<ProductTypeDTO> types = typeService.loadCompanyProductTypes(request.getUserId());
			response.setProductTypes(types);
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("获得产品类型失败.", e);
		}
		return response;
	}
	
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
