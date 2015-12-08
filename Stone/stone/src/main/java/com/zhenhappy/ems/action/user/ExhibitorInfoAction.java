package com.zhenhappy.ems.action.user;

import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.*;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TExhibitorMeipai;
import com.zhenhappy.ems.entity.TExhibitorMsg;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.ems.service.MeipaiService;
import com.zhenhappy.ems.service.MsgService;
import com.zhenhappy.ems.service.ProductService;
import com.zhenhappy.ems.sys.Constants;
import com.zhenhappy.system.SystemConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wujianbin on 2014-04-08.
 */
@Controller
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
@RequestMapping(value = "user")
public class ExhibitorInfoAction extends BaseAction {

    @Autowired
    private MeipaiService meipaiService;

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private MsgService msgService;

    private static Logger log = Logger.getLogger(ExhibitorInfoAction.class);

    /**
     * redirect to information manager.service page.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ModelAndView redirectInfo(HttpServletRequest request, Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
        Integer exhibitorId = principle.getExhibitor().getEid();
        TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(exhibitorId);
		if (exhibitorInfo == null){
            List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
//            List<ProductTypeCheck> productTypeChecks = exhibitorService.loadAllProductTypesWithCheck(exhibitorInfo.getEinfoid());
            List<ProductTypeCheck> productTypeChecks = Collections.emptyList();
            if (locale.equals(Locale.US)) {
                for (ProductType productType : productTypes) {
                    productType.setTypeName(productType.getTypeNameEN());
                    for (ProductType type : productType.getChildrenTypes()) {
                        type.setTypeName(type.getTypeNameEN());
                    }
                }
                modelAndView.setViewName("/user/info/insert_en");
            } else {
                modelAndView.setViewName("/user/info/insert");
            }
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
        } else {
            List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
			List<ProductTypeCheck> productTypeChecks = null;
			if (exhibitorInfo.getCompany() == null && exhibitorInfo.getCompanyEn() == null){
				productTypeChecks = Collections.emptyList();
			}else{
            	productTypeChecks = exhibitorService.loadAllProductTypesWithCheck(exhibitorInfo.getEinfoid());
			}
            if (locale.equals(Locale.US)) {
                for (ProductType productType : productTypes) {
                    productType.setTypeName(productType.getTypeNameEN());
                    for (ProductType type : productType.getChildrenTypes()) {
                        type.setTypeName(type.getTypeNameEN());
                    }
                }
                modelAndView.setViewName("/user/info/update_en");
            } else {
                modelAndView.setViewName("/user/info/update");
            }
			TExhibitor exhibitor = exhibitorService.getExhibitorByEid(exhibitorId);
            modelAndView.addObject("exhibitor", exhibitor);
			modelAndView.addObject("area", exhibitor.getArea());
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            modelAndView.addObject("updateInfo", exhibitorInfo);
        }
        return modelAndView;
    }

    /**
     * create current user's information
     *
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    public ModelAndView addInfo(@ModelAttribute(value = "info") TExhibitorInfo exhibitorInfo, @RequestParam("companyLogo") MultipartFile companyLogo, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
            exhibitorInfo.setEid(principle.getExhibitor().getEid());
            String fileName = systemConfig.getVal(Constants.appendix_directory) + "/" + new Date().getTime() + "." + FilenameUtils.getExtension(companyLogo.getOriginalFilename());
            FileUtils.copyInputStreamToFile(companyLogo.getInputStream(), new File(fileName));
            exhibitorInfo.setLogo(fileName);
            exhibitorInfo.setIsDelete(0);
            exhibitorInfo.setCreateTime(new Date());
            exhibitorService.insert(exhibitorInfo);
            exhibitorService.saveExhibitorProductClass(JSONObject.parseObject(exhibitorInfo.getClassjson(), SaveExhibitorSelectRequest.class).getSelected(), exhibitorInfo.getEinfoid());
            List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
            List<ProductTypeCheck> productTypeChecks = exhibitorService.loadAllProductTypesWithCheck(exhibitorInfo.getEinfoid());
            if (locale.equals(Locale.US)) {
                for (ProductType productType : productTypes) {
                    productType.setTypeName(productType.getTypeNameEN());
                    for (ProductType type : productType.getChildrenTypes()) {
                        type.setTypeName(type.getTypeNameEN());
                    }
                }
                modelAndView.setViewName("/user/info/update_en");
                modelAndView.addObject("alert", "Add Success");
            } else {
                modelAndView.addObject("alert", "添加成功");
                modelAndView.setViewName("/user/info/update");
            }
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            modelAndView.addObject("info", exhibitorInfo);
        } catch (Exception e) {
            log.error("create exhibitor information error.", e);
            throw new IllegalArgumentException("数据填写错误");
        }
        return modelAndView;
    }

    /**
     * update information
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateinfo", method = RequestMethod.POST)
    public ModelAndView updateInfo(@ModelAttribute TExhibitorInfo exhibitorInfo, @RequestParam("companyLogo") MultipartFile companyLogo, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
            TExhibitor exhibitor = exhibitorService.getExhibitorByEid(principle.getExhibitor().getEid());
            exhibitorInfo.setEid(principle.getExhibitor().getEid());
            if (companyLogo != null && companyLogo.getSize() != 0) {
                String fileName = systemConfig.getVal(Constants.appendix_directory) + "/" + new Date().getTime() + "." + FilenameUtils.getExtension(companyLogo.getOriginalFilename());
                FileUtils.copyInputStreamToFile(companyLogo.getInputStream(), new File(fileName));
                exhibitorInfo.setLogo(fileName);
            }
            TExhibitorInfo info = exhibitorService.loadExhibitorInfoByEid(exhibitorInfo.getEid());
            exhibitorInfo.setCompany(exhibitor.getCompany());
            exhibitorInfo.setCompanyEn(exhibitor.getCompanye());
            exhibitorInfo.setCreateTime(info.getCreateTime());
            exhibitorInfo.setIsDelete(info.getIsDelete());
            exhibitorInfo.setAdminUpdateTime(info.getAdminUpdateTime());
            exhibitorInfo.setAdminUser(info.getAdminUser());
            exhibitorInfo.setUpdateTime(new Date());
            exhibitorService.update(exhibitorInfo);
            exhibitorService.saveExhibitorProductClass(JSONObject.parseObject(exhibitorInfo.getClassjson(), SaveExhibitorSelectRequest.class).getSelected(), exhibitorInfo.getEinfoid());
            List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
            List<ProductTypeCheck> productTypeChecks = exhibitorService.loadAllProductTypesWithCheck(exhibitorInfo.getEinfoid());
            if (locale.equals(Locale.US)) {
                for (ProductType productType : productTypes) {
                    productType.setTypeName(productType.getTypeNameEN());
                    for (ProductType type : productType.getChildrenTypes()) {
                        type.setTypeName(type.getTypeNameEN());
                    }
                }
                modelAndView.setViewName("/user/info/update_en");
                modelAndView.addObject("alert", "Modify Success");
            } else {
                modelAndView.addObject("alert", "修改成功");
                modelAndView.setViewName("/user/info/update");
            }
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            modelAndView.addObject("updateInfo", exhibitorInfo);
            modelAndView.addObject("redirect", "/user/info");
            modelAndView.addObject("exhibitor", exhibitor);
        } catch (Exception e) {
            log.error("update exhibitor information error.", e);
        }
        return modelAndView;
    }

    /**
     * open exhibitor manage page.
     *
     * @return
     */
    @RequestMapping(value = "exhibitorclass", method = RequestMethod.GET)
    public ModelAndView exhibitorClass(HttpServletRequest request, Locale locale) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
            Integer exhibitorId = principle.getExhibitor().getEid();
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(exhibitorId);
            if (exhibitorInfo == null) {
                modelAndView.addObject("alert", "请先填写展商基本信息");
                modelAndView.setViewName("/user/info/insert");
            } else {
                modelAndView.setViewName("/user/info/class");
                List<ProductType> productTypes = exhibitorService.loadAllProductTypes();
                List<ProductTypeCheck> productTypeChecks = exhibitorService.loadAllProductTypesWithCheck(exhibitorInfo.getEinfoid());
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "exhibitorclass", method = RequestMethod.POST)
    public BaseResponse saveExhibitorClass(@RequestBody SaveExhibitorSelectRequest saveExhibitorSelectRequest, HttpServletRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
            Integer exhibitorId = principle.getExhibitor().getEid();
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(exhibitorId);
            exhibitorService.saveExhibitorProductClass(saveExhibitorSelectRequest.getSelected(), exhibitorInfo.getEinfoid());
        } catch (Exception e) {
            log.error("save exhibitor product type error.", e);
            response.setResultCode(1);
            response.setDescription("保存失败");
        }
        return response;
    }

    /**
     * load exhibitor image.
     *
     * @param response
     * @param eid
     */
    @RequestMapping(value = "logoImg", method = RequestMethod.GET)
    public void logoImg(HttpServletResponse response, @RequestParam("eid") Integer eid) {
        try {
            String logoFileName = exhibitorService.loadExhibitorInfoByEid(eid).getLogo();
            if (StringUtils.isNotEmpty(logoFileName)) {
                OutputStream outputStream = response.getOutputStream();
                File logo = new File(logoFileName);
                if (!logo.exists()) {
                    return;
                }
                FileUtils.copyFile(new File(logoFileName), outputStream);
                outputStream.close();
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request) {
        Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
        Long count = msgService.countUnreadMessage(principle.getExhibitor().getEid());
        ModelAndView modelAndView = new ModelAndView("/user/main");
        modelAndView.addObject("unReadMessageCount", count);
        return modelAndView;
    }

    @RequestMapping(value = "password", method = RequestMethod.GET)
    public ModelAndView redirectPassword() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("user/info/modifyPassword");
        } catch (Exception e) {
            throw e;
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
    public BaseResponse modifyPassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) {
        Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
        Integer exhibitorId = principle.getExhibitor().getEid();
        BaseResponse response = new BaseResponse();
        try {
            exhibitorService.modifyPassword(exhibitorId, newPassword, oldPassword);
        } catch (Exception e) {
            response.setResultCode(1);
            response.setDescription(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "banners", method = RequestMethod.GET)
    public ModelAndView bannerPrint(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        ModelAndView modelAndView = new ModelAndView("/user/banner/index");
        modelAndView.addObject("eid", principle.getExhibitor().getEid());
        return modelAndView;
    }

    @RequestMapping(value = "preview")
    public ModelAndView preview(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        ModelAndView modelAndView = new ModelAndView("/user/preview/preview");
        Integer exhibitorId = principle.getExhibitor().getEid();
        TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(exhibitorId);
        modelAndView.addObject("info", exhibitorInfo);
        if (exhibitorInfo != null) {
            modelAndView.addObject("products", productService.previewProducts(exhibitorInfo.getEinfoid()));
        }
        return modelAndView;
    }

    @RequestMapping(value = "addMeipai", method = RequestMethod.GET)
    public ModelAndView addMeipai(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        ModelAndView modelAndView = null;
        Integer exhibitorId = principle.getExhibitor().getEid();
        TExhibitorMeipai meipai = meipaiService.getMeiPaiByEid(principle.getExhibitor().getEid());
        if (meipai == null) {
            modelAndView = new ModelAndView("user/meipai/add");
        } else {
            modelAndView = new ModelAndView("user/meipai/update");
            modelAndView.addObject("meipai", meipai);
        }
        return modelAndView;
    }

    @RequestMapping(value = "addMeipai", method = RequestMethod.POST)
    public ModelAndView saveMeipai(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "ename", required = false) String ename, @RequestParam(value = "mid", required = false) Integer mid, Locale locale) {
        ModelAndView modelAndView = null;
        Integer exhibitorId = principle.getExhibitor().getEid();
        TExhibitorMeipai meipai = new TExhibitorMeipai();
        meipai.setExhibitorId(exhibitorId);
        meipai.setMeipai(name);
        meipai.setMeipaiEn(ename);
        meipai.setCreateTime(new Date());
        meipai.setUpdateTime(new Date());
        meipai.setId(mid);
        meipaiService.saveOrUpdate(meipai);
        modelAndView = new ModelAndView("user/meipai/update");
        if (locale.equals(Locale.US)) {
            modelAndView.addObject("alert", "Operation success!");
        } else {
            modelAndView.addObject("alert", "操作成功！");
        }
        modelAndView.addObject("meipai", meipai);
        return modelAndView;
    }
}
