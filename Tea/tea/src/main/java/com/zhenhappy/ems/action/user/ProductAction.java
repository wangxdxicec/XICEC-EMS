package com.zhenhappy.ems.action.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.*;
import com.zhenhappy.ems.entity.TBrand;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TProductImage;
import com.zhenhappy.ems.service.BrandService;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.ems.service.ProductService;
import com.zhenhappy.ems.sys.Constants;
import com.zhenhappy.system.SystemConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by wujianbin on 2014-04-10.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
public class ProductAction extends BaseAction {

    @Autowired
    private ProductService productService;

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SystemConfig systemConfig;

    private Logger log = Logger.getLogger(ProductAction.class);

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public ModelAndView queryProducts(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, Locale locale) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(principle.getExhibitor().getEid());
            if (exhibitorInfo == null) {
                List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
                List<ProductTypeCheck> productTypeChecks = Collections.emptyList();
                if (locale.equals(Locale.US)) {
                    for (ProductType productType : productTypes) {
                        productType.setTypeName(productType.getTypeNameEN());
                        for (ProductType type : productType.getChildrenTypes()) {
                            type.setTypeName(type.getTypeNameEN());
                        }
                    }
                }
                modelAndView.addObject("types", productTypes);
                modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
                String warn = locale.toString().equals("zh_CN") ? "请先填写展商基本信息" : "Please fill your company information before add products";
                modelAndView.addObject("alert", warn);
                modelAndView.addObject("redirect", "/user/info");
                modelAndView.setViewName("/user/info/insert");
            } else {
                List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
                List<TBrand> brands = brandService.loadAllBrandsByEid(principle.getExhibitor().getEid());
                if (locale.equals(Locale.US)) {
                    for (ProductType productType : productTypes) {
                        productType.setTypeName(productType.getTypeNameEN());
                        for (ProductType type : productType.getChildrenTypes()) {
                            type.setTypeName(type.getTypeNameEN());
                        }
                    }
                }
                modelAndView.addObject("brands", brands);
                modelAndView.addObject("types", productTypes);
                modelAndView.setViewName("user/product/addproduct");
            }
        } catch (Exception e) {
            log.error("query products error.", e);
            throw e;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute ProductDto productDto,@RequestParam(value = "brandId",required = false) Integer brandId, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, @RequestParam("productLogo") MultipartFile[] productLogos) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(principle.getExhibitor().getEid());
            List<String> fileNames = new ArrayList<String>(productLogos.length);
            for (MultipartFile productLogo : productLogos) {
                String fileName = systemConfig.getVal(Constants.appendix_directory) + "/" + new Date().getTime() + "." + FilenameUtils.getExtension(productLogo.getOriginalFilename());
                if (productLogo != null && productLogo.getSize() != 0) {
                    FileUtils.copyInputStreamToFile(productLogo.getInputStream(), new File(fileName));
                }
                fileNames.add(fileName);
            }
            if (StringUtils.isNotEmpty(productDto.getSelectString())) {
                productDto.setSelected(JSONArray.parseArray(productDto.getSelectString(), ProductTypeCheck.class));
            }
            brandService.addProductWithBrand(productDto,exhibitorInfo.getEinfoid(),fileNames,brandId,principle.getExhibitor());
            modelAndView.setViewName("/user/callback");
            modelAndView.addObject("method", "addSuccess");
        } catch (Exception e) {
            log.error("添加产品失败", e);
            modelAndView.setViewName("/user/callback");
            modelAndView.addObject("method", "addFailure");
        }
        return modelAndView;
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public ModelAndView products(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(principle.getExhibitor().getEid());
        if (exhibitorInfo == null) {
            List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
            List<ProductTypeCheck> productTypeChecks = Collections.emptyList();
            if (locale.equals(Locale.US)) {
                for (ProductType productType : productTypes) {
                    productType.setTypeName(productType.getTypeNameEN());
                    for (ProductType type : productType.getChildrenTypes()) {
                        type.setTypeName(type.getTypeNameEN());
                    }
                }
            }
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            String warn = locale.toString().equals("zh_CN") ? "请先填写展商基本信息" : "Please fill your company information before add products";
            modelAndView.addObject("alert", warn);
            modelAndView.addObject("redirect", "/user/info");
            modelAndView.setViewName("/user/info/insert");
        } else {
            modelAndView.setViewName("/user/product/products");
            List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
            if (locale.equals(Locale.US)) {
                for (ProductType productType : productTypes) {
                    productType.setTypeName(productType.getTypeNameEN());
                    for (ProductType type : productType.getChildrenTypes()) {
                        type.setTypeName(type.getTypeNameEN());
                    }
                }
            }
            modelAndView.addObject("types", productTypes);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "queryProducts", method = RequestMethod.POST)
    public ProductQueryDto queryProducts(@RequestBody ProductQueryDto request, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        try {
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(principle.getExhibitor().getEid());
            productService.queryProducts(request, exhibitorInfo.getEinfoid());
        } catch (Exception e) {
            e.printStackTrace();
            request.setResultCode(1);
        }
        return request;
    }

    /**
     * Get which types include this product.
     *
     * @param pid
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "productClassSelect", method = RequestMethod.POST)
    public GetProductClassSelectResponse getProductClassSelect(@RequestParam("pid") Integer pid, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        GetProductClassSelectResponse response = new GetProductClassSelectResponse();
        try {
            List<ProductTypeCheck> checks = productService.loadAllProductTypesWithCheck(pid);
            response.setResultCode(0);
            response.setSelected(checks);
        } catch (Exception e) {
            log.error("加载产品所属分类失败", e);
            response.setResultCode(1);
        }
        return response;
    }

    /**
     * modify the product's types
     *
     * @param productDto
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyTypes", method = RequestMethod.POST)
    public BaseResponse modifyProductType(@RequestBody ProductDto productDto, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            Integer exhibitorId = principle.getExhibitor().getEid();
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(exhibitorId);
            if (StringUtils.isNotEmpty(productDto.getSelectString())) {
                productDto.setSelected(JSONArray.parseArray(productDto.getSelectString(), ProductTypeCheck.class));
            }
            productService.modifyProductTypes(productDto, exhibitorInfo.getEinfoid());
        } catch (Exception e) {
            log.error("modify product types error.", e);
            response.setResultCode(1);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
    public BaseResponse deleteProduct(@RequestParam("pid") Integer pid, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(principle.getExhibitor().getEid());
        BaseResponse response = new BaseResponse();
        try {
            productService.deleteProduct(pid, exhibitorInfo.getEinfoid());
            response.setResultCode(0);
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "queryProductPics", method = RequestMethod.POST)
    public ProductPicsResponse queryProductPics(@RequestParam("pid") Integer pid) {
        ProductPicsResponse response = new ProductPicsResponse();
        try {
            List<Integer> pics = productService.queryProductPics(pid);
            response.setResultCode(0);
            response.setPids(pics);
        } catch (Exception e) {
            log.error("query product:" + pid + " pictures error.");
        }
        return response;
    }

    @RequestMapping(value = "productImg", method = RequestMethod.GET)
    public void logoImg(HttpServletResponse response, @RequestParam("pid") Integer pid) {
        try {
            TProductImage image = productService.getProductLogo(pid);
            if (image != null) {
                OutputStream outputStream = response.getOutputStream();
                File img = new File(image.getImage());
                if (!img.exists()) {
                    return;
                }
                FileUtils.copyFile(new File(image.getImage()), outputStream);
                outputStream.close();
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "modifyProduct", method = RequestMethod.POST)
    public ModelAndView modifyProduct(@ModelAttribute ProductDto productDto, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, @RequestParam(value = "productLogo", required = false) MultipartFile[] productLogos) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(principle.getExhibitor().getEid());
            List<String> fileNames = new ArrayList<String>();
            if (productLogos != null) {
                for (MultipartFile productLogo : productLogos) {
                    String fileName = systemConfig.getVal(Constants.appendix_directory) + "/" + new Date().getTime() + "." + FilenameUtils.getExtension(productLogo.getOriginalFilename());
                    if (productLogo != null && productLogo.getSize() != 0) {
                        FileUtils.copyInputStreamToFile(productLogo.getInputStream(), new File(fileName));
                        fileNames.add(fileName);
                    }
                }
            }
            List<Integer> imageIds = null;
            if (StringUtils.isNotEmpty(productDto.getImageIds())) {
                imageIds = JSONArray.parseArray(productDto.getImageIds(), Integer.class);
            }
            productService.modifyProduct(productDto, imageIds, fileNames, exhibitorInfo.getEinfoid());
            modelAndView.setViewName("/user/callback");
            modelAndView.addObject("method", "addSuccess");
        } catch (Exception e) {
            log.error("修改产品失败", e);
            modelAndView.setViewName("/user/callback");
            modelAndView.addObject("method", "addFailure");
        }
        return modelAndView;
    }
}
