package com.zhenhappy.ems.manager.action.user;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.dto.QueryProductType2Response;
import com.zhenhappy.ems.dto.QueryProductTypeResponse;
import com.zhenhappy.ems.entity.TExhibitorClass;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TProduct;
import com.zhenhappy.ems.entity.TProductType;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.AddProductRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyProductRequest;
import com.zhenhappy.ems.manager.dto.QueryProductsRequest;
import com.zhenhappy.ems.manager.dto.QueryProductsResponse;
import com.zhenhappy.ems.manager.service.ExhibitorManagerService;
import com.zhenhappy.ems.manager.service.ProductManagerService;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class ProductAction extends BaseAction {

    private static Logger log = Logger.getLogger(ProductAction.class);

    @Autowired
    private ProductManagerService productManagerService;
    @Autowired
    private ExhibitorManagerService exhibitorManagerService;

    @ResponseBody
    @RequestMapping(value = "queryProducts", method = RequestMethod.POST)
    public QueryProductsResponse queryProducts(@ModelAttribute QueryProductsRequest request) {
        QueryProductsResponse response = null;
        try {
            TExhibitorInfo exhibitorInfo = exhibitorManagerService.loadExhibitorInfoByEid(request.getEid());
            if (exhibitorInfo != null) {
                response = productManagerService.queryProducts(request, exhibitorInfo.getEinfoid());
            } else {
                response = new QueryProductsResponse();
                response.setRows(new ArrayList());
            }
        } catch (Exception e) {
            response = new QueryProductsResponse();
            log.error("query products error.",e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public BaseResponse addProduct(@ModelAttribute AddProductRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            TProduct product = new TProduct();
            product.setEinfoId(request.getEinfoId());
            product.setProductName(request.getProductName());
            product.setProductModel(request.getProductModel());
            product.setOrigin(request.getOrigin());
            product.setKeyWords(request.getKeyWords());
            product.setDescription(request.getDescription());
            product.setProductBrand(request.getProductBrand());
            product.setProductSpec(request.getProductSpec());
            product.setProductCount(request.getProductCount());
            product.setPackageDescription(request.getPackageDescription());
            product.setPriceDescription(request.getPriceDescription());
            product.setFlag(request.getFlag());
            product.setCreateTime(new Date());
            if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	product.setAdmin(principle.getAdmin().getId());
            }
            productManagerService.addProduct(product);
        } catch (Exception e) {
        	log.error("add product error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "modifyProduct", method = RequestMethod.POST)
    public BaseResponse modifyProduct(@ModelAttribute ModifyProductRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            TProduct product = productManagerService.loadProductById(request.getId());
            product.setEinfoId(request.getEinfoId());
            product.setProductName(request.getProductName());
            product.setProductModel(request.getProductModel());
            product.setOrigin(request.getOrigin());
            product.setKeyWords(request.getKeyWords());
            product.setDescription(request.getDescription());
            product.setProductBrand(request.getProductBrand());
            product.setProductSpec(request.getProductSpec());
            product.setProductCount(request.getProductCount());
            product.setPackageDescription(request.getPackageDescription());
            product.setPriceDescription(request.getPriceDescription());
            product.setFlag(request.getFlag());
            product.setUpdateTime(new Date());
            if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	product.setAdmin(principle.getAdmin().getId());
            	product.setAdminUpdateTime(new Date());
            }
            productManagerService.modifyProduct(product);
        } catch (Exception e) {
        	log.error("modify product error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "removeProducts", method = RequestMethod.POST)
    public BaseResponse removeProducts(@RequestParam(value = "pids", defaultValue = "") Integer[] pids) {
        BaseResponse response = new BaseResponse();
        try {
        	if(pids == null) throw new Exception();
        	productManagerService.removeProductsByPIds(pids);
        } catch (Exception e) {
        	log.error("remove products error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    

    @ResponseBody
    @RequestMapping(value = "queryProductType", method = RequestMethod.POST)
    public List<QueryProductTypeResponse> queryProductType() {
    	List<QueryProductTypeResponse> response = new ArrayList<QueryProductTypeResponse>();
        try {
        	List<TProductType> productTypes1 = productManagerService.queryProductsTypeByLv(1);
        	List<TProductType> productTypes2 = productManagerService.queryProductsTypeByLv(2);
        	for(TProductType productType1:productTypes1){
        		List<QueryProductType2Response> children = new ArrayList<QueryProductType2Response>();
        		for(TProductType productType2:productTypes2){
        			if(productType1.getId().intValue() == productType2.getParentId().intValue()){
        				QueryProductType2Response p = new QueryProductType2Response(productType2.getId(),productType2.getClassName(),"icon-ok",false);
        				children.add(p);
        			}
        		}
        		response.add(new QueryProductTypeResponse(productType1.getId(),productType1.getClassName(),"icon-ok","closed",false,children));
        	}
        } catch (Exception e) {
        	log.error("query product type error.", e);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "queryProductTypeByEid", method = RequestMethod.POST)
    public List<QueryProductTypeResponse> queryProductTypeByEid(@RequestParam("einfoid") Integer einfoid) {
    	List<TExhibitorClass> exhibitorClasses = exhibitorManagerService.queryExhibitorClassByEid(einfoid);
    	List<QueryProductTypeResponse> response = new ArrayList<QueryProductTypeResponse>();
        try {
        	if(exhibitorClasses.size() != 0){
        		List<TProductType> productTypes1 = productManagerService.queryProductsTypeByLv(1);
            	List<TProductType> productTypes2 = productManagerService.queryProductsTypeByLv(2);
            	for(TProductType productType1:productTypes1){
            		List<QueryProductType2Response> children = new ArrayList<QueryProductType2Response>();
            		for(TProductType productType2:productTypes2){
            			if(productType1.getId().intValue() == productType2.getParentId().intValue()){
//            				System.out.println(productType1.getId().intValue() + productType1.getClassName() + "<--" + productType2.getId().intValue() + productType2.getClassName());
            				QueryProductType2Response p = new QueryProductType2Response(productType2.getId(),productType2.getClassName(),"icon-ok",false);
            				for(TExhibitorClass exhibitorClass:exhibitorClasses){
            					if(productType1.getId().intValue() == exhibitorClass.getParentClassId()){
            						if(p.getId().intValue() == exhibitorClass.getClassId()){
//            							System.out.println(exhibitorClass.getParentClassId() + "<--" + exhibitorClass.getClassId());
            							p.setChecked(true);
            						}
            					}
            				}
            				children.add(p);
            			}
            		}
            		response.add(new QueryProductTypeResponse(productType1.getId(),productType1.getClassName(),"icon-ok","closed",false,children));
            	}
        	}else{
        		List<TProductType> productTypes1 = productManagerService.queryProductsTypeByLv(1);
            	List<TProductType> productTypes2 = productManagerService.queryProductsTypeByLv(2);
            	for(TProductType productType1:productTypes1){
            		List<QueryProductType2Response> children = new ArrayList<QueryProductType2Response>();
            		for(TProductType productType2:productTypes2){
            			if(productType1.getId().intValue() == productType2.getParentId().intValue()){
            				QueryProductType2Response p = new QueryProductType2Response(productType2.getId(),productType2.getClassName(),"icon-ok",false);
            				children.add(p);
            			}
            		}
            		response.add(new QueryProductTypeResponse(productType1.getId(),productType1.getClassName(),"icon-ok","closed",false,children));
            	}
        	}
        } catch (Exception e) {
        	log.error("query product type error.", e);
        }
        return response;
    }

	/**
	 * 获取产品图片
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "showProductImg", method = RequestMethod.GET)
	public void showProductImg(HttpServletResponse response,
							   @RequestParam("id") Integer id,
							   @RequestParam(value = "w", defaultValue = "0") Integer w,
							   @RequestParam(value = "h", defaultValue = "0") Integer h) {
		try {
			String productImgPath = productManagerService.loadProductImgById(id);
			if (StringUtils.isNotEmpty(productImgPath)) {
				File productImg = new File(productImgPath);
				if (!productImg.exists()) return;
				OutputStream outputStream = response.getOutputStream();
				if(w == 0 && h == 0){
					FileUtils.copyFile(new File(productImgPath), outputStream);
				}else{
					Thumbnails.of(productImg)
							.size(w, h)
							.toOutputStream(outputStream);
				}
				outputStream.close();
				outputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
