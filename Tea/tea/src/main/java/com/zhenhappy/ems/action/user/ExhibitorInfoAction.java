package com.zhenhappy.ems.action.user;

import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.*;
import com.zhenhappy.ems.entity.TBrand;
import com.zhenhappy.ems.entity.TExhibitorBooth;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TExhibitorMeipai;
import com.zhenhappy.ems.service.*;
import com.zhenhappy.ems.sys.Constants;
import com.zhenhappy.system.SystemConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MeipaiService meipaiService;

    @Autowired
    private BrandService brandService;

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
        if (exhibitorInfo == null) {
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
            }else{
                modelAndView.setViewName("/user/info/insert");
            }
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
        } else {
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
            }else{
                modelAndView.setViewName("/user/info/update");
            }
            List<TBrand> brands = brandService.loadBrandsByEid(exhibitorId);
            modelAndView.addObject("brands", brands);
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            modelAndView.addObject("newinfo", exhibitorInfo);
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
            brandService.saveExhibitorAndBrands(exhibitorInfo);
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
                modelAndView.addObject("alert", "Add success");
            }else{
                modelAndView.addObject("alert", "添加成功");
                modelAndView.setViewName("/user/info/update");
            }
            List<TBrand> brands = brandService.loadBrandsByEid(principle.getExhibitor().getEid());
            modelAndView.addObject("brands", brands);
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            modelAndView.addObject("newinfo", exhibitorInfo);
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
    public ModelAndView updateInfo(@ModelAttribute TExhibitorInfo exhibitorInfo, @RequestParam("companyLogo1") MultipartFile companyLogo1, @RequestParam("companyLogo2") MultipartFile companyLogo2, HttpServletRequest request, HttpServletResponse response, Locale locale) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        String returnStrCn = "修改成功";
        String returnStrEn = "Modify success";
        try {
            Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
            exhibitorInfo.setEid(principle.getExhibitor().getEid());
            if (companyLogo1 != null && companyLogo1.getSize() > 0 && companyLogo1.getSize() <= 204800) {
                String fileName1 = systemConfig.getVal(Constants.appendix_directory) + "/logo/" + new Date().getTime() + "." + FilenameUtils.getExtension(companyLogo1.getOriginalFilename());
                FileUtils.copyInputStreamToFile(companyLogo1.getInputStream(), new File(fileName1));
                if(StringUtils.isNotEmpty(exhibitorInfo.getLogo())) {
                    if(exhibitorInfo.getLogo().contains(";")){
                        String[] name = exhibitorInfo.getLogo().split(";");
                        if(name.length == 2){
                            exhibitorInfo.setLogo(fileName1 + ";" + name[1]);
                        }else{
                            exhibitorInfo.setLogo(fileName1 + ";null");
                        }
                    }else{
                        exhibitorInfo.setLogo(fileName1 + ";null");
                    }
                }else{
                    exhibitorInfo.setLogo(fileName1 + ";null");
                }
            }else if(companyLogo1.getSize() > 204800){
                returnStrCn = "前一个公司Logo大于200k";
                returnStrEn = "Logo1 bigger 200k";
            }
            if (companyLogo2 != null && companyLogo2.getSize() > 0 && companyLogo2.getSize() <= 204800) {
                String fileName2 = systemConfig.getVal(Constants.appendix_directory) + "/logo/" + new Date().getTime() + "." + FilenameUtils.getExtension(companyLogo2.getOriginalFilename());
                FileUtils.copyInputStreamToFile(companyLogo2.getInputStream(), new File(fileName2));
                if(StringUtils.isNotEmpty(exhibitorInfo.getLogo())) {
                    if(exhibitorInfo.getLogo().contains(";")){
                        String[] name = exhibitorInfo.getLogo().split(";");
                        if(name.length == 2){
                            exhibitorInfo.setLogo(name[0] + ";" + fileName2);
                        }else{
                            exhibitorInfo.setLogo("null;" + fileName2);
                        }
                    }else{
                        exhibitorInfo.setLogo("null;" + fileName2);
                    }
                }else{
                    exhibitorInfo.setLogo("null;" + fileName2);
                }
            }else if(companyLogo2.getSize() > 204800){
                returnStrCn = "后一个公司Logo大于200k";
                returnStrEn = "Logo2 bigger 200k";
            }
            TExhibitorInfo info = exhibitorService.loadExhibitorInfoByEid(exhibitorInfo.getEid());
            exhibitorInfo.setCreateTime(info.getCreateTime());
            exhibitorInfo.setIsDelete(info.getIsDelete());
            exhibitorInfo.setAdminUpdateTime(info.getAdminUpdateTime());
            exhibitorInfo.setAdminUser(info.getAdminUser());
            exhibitorInfo.setUpdateTime(new Date());
            brandService.updateExhibitorAndBrands(exhibitorInfo);
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
                modelAndView.setViewName("/user/info/update");
                modelAndView.addObject("alert", returnStrEn);
            }else{
                modelAndView.addObject("alert", returnStrCn);
                modelAndView.setViewName("/user/info/update_en");
            }
            List<TBrand> brands = brandService.loadBrandsByEid(principle.getExhibitor().getEid());
            modelAndView.addObject("brands", brands);
            modelAndView.addObject("types", productTypes);
            modelAndView.addObject("selected", JSONObject.toJSONString(productTypeChecks));
            modelAndView.addObject("newinfo", exhibitorInfo);
            modelAndView.addObject("redirect", "/user/info");
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
                if(locale.equals(Locale.US)){
                    modelAndView.addObject("alert", "Please fill your company information first!");
                }else{
                    modelAndView.addObject("alert", "请先填写展商基本信息！");
                }
                if (locale.equals(Locale.US)) {
                    modelAndView.setViewName("/user/info/insert_en");
                }else{
                    modelAndView.setViewName("/user/info/insert");
                }
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
    public void logoImg(HttpServletResponse response, @RequestParam("eid") Integer eid, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            String logoFileName = exhibitorService.loadExhibitorInfoByEid(eid).getLogo();
            if(logoFileName.contains(";")){
                String[] logos = logoFileName.split(";");
                if (StringUtils.isNotEmpty(logos[page])) {
                    OutputStream outputStream = response.getOutputStream();
                    File logo = new File(logos[page]);
                    if (!logo.exists()) {
                        return;
                    }
                    FileUtils.copyFile(new File(logos[page]), outputStream);
                    outputStream.close();
                    outputStream.flush();
                }
            }else if(logoFileName.contains("appendix")){
                File logo = new File(logoFileName);
                if (!logo.exists()) return;
                OutputStream outputStream = response.getOutputStream();
                FileUtils.copyFile(logo, outputStream);
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
        List<TExhibitorBooth> booths = hibernateTemplate.find("from TExhibitorBooth where eid = ?", principle.getExhibitor().getEid());
        if(booths.size() > 0) modelAndView.addObject("boothInfo", booths.get(0));
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
        TExhibitorMeipai meipai = meipaiService.getMeiPaiByEid(principle.getExhibitor().getEid());
        List<TExhibitorBooth> booths = hibernateTemplate.find("from TExhibitorBooth where eid = ?", principle.getExhibitor().getEid());
        if (meipai == null) {
            modelAndView = new ModelAndView("user/meipai/add");
        } else {
            modelAndView = new ModelAndView("user/meipai/update");
            modelAndView.addObject("meipai", meipai);
        }
        if(booths.size() > 0) modelAndView.addObject("booth", booths.get(0).getBoothNumber());
        else modelAndView.addObject("booth", "null");
        return modelAndView;
    }

    @RequestMapping(value = "addMeipai", method = RequestMethod.POST)
    public ModelAndView saveMeipai(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle, @RequestParam(value = "name",required = false) String name,@RequestParam(value = "ename",required = false) String ename, @RequestParam(value = "mid", required = false) Integer mid, Locale locale) {
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
        if(locale.equals(Locale.US)){
            modelAndView.addObject("alert", "Operation success!");
        }else{
            modelAndView.addObject("alert", "操作成功！");
        }
        modelAndView.addObject("meipai", meipai);
        return modelAndView;
    }

}
