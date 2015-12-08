package com.zhenhappy.ems.manager.action.user;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.WCountry;
import com.zhenhappy.ems.entity.WProvince;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.ActiveExhibitorRequest;
import com.zhenhappy.ems.manager.dto.AddExhibitorRequest;
import com.zhenhappy.ems.manager.dto.BindBoothRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyExhibitorRequest;
import com.zhenhappy.ems.manager.dto.ModifyExhibitorInfoRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorResponse;
import com.zhenhappy.ems.manager.entity.TExhibitorBooth;
import com.zhenhappy.ems.manager.entity.TExhibitorTerm;
import com.zhenhappy.ems.manager.exception.DuplicateUsernameException;
import com.zhenhappy.ems.manager.service.ExhibitorManagerService;
import com.zhenhappy.ems.manager.service.TagManagerService;
import com.zhenhappy.ems.service.CountryProvinceService;
import com.zhenhappy.ems.service.InvoiceService;
import com.zhenhappy.ems.service.MeipaiService;
import com.zhenhappy.system.SystemConfig;

/**
 * Created by wujianbin on 2014-04-22.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class ExhibitorAction extends BaseAction {

	private static Logger log = Logger.getLogger(ExhibitorAction.class);

    @Autowired
    private ExhibitorManagerService exhibitorManagerService;
    @Autowired
    private TagManagerService tagManagerService;
    @Autowired
    private MeipaiService meipaiService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CountryProvinceService countryProvinceService;
    @Autowired
    private ImportExportAction importExportAction;
    @Autowired
    private SystemConfig systemConfig;
    
    /**
     * 分页查询展商列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryExhibitorsByPage")
    public QueryExhibitorResponse queryExhibitorsByPage(@ModelAttribute QueryExhibitorRequest request) {
        QueryExhibitorResponse response = null;
        try {
        	response = exhibitorManagerService.queryExhibitorsByPage(request);
        } catch (Exception e) {
            response.setResultCode(1);
            log.error("query exhibitors error.", e);
        }
        return response;
    }
    
    /**
     * 查询展商列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryExhibitors")
    public List<TExhibitor> queryExhibitors() {
    	List<TExhibitor> response = new ArrayList<TExhibitor>();
        try {
        	response = exhibitorManagerService.loadAllExhibitors();
        } catch (Exception e) {
            log.error("query exhibitors error.", e);
        }
        return response;
    }
    
    /**
     * 根据eid查询展商
     * @param eid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryExhibitorByEid")
    public TExhibitor queryExhibitorByEid(@RequestParam("eid") Integer eid) {
    	TExhibitor response = new TExhibitor();
        try {
        	response = exhibitorManagerService.loadExhibitorByEid(eid);
        } catch (Exception e) {
            log.error("query exhibitors error.", e);
        }
        return response;
    }

    @RequestMapping(value = "exhibitor")
    public ModelAndView directToCompany(@RequestParam("eid") Integer eid) {
        ModelAndView modelAndView = new ModelAndView("user/exhibitor/company");
        modelAndView.addObject("eid", eid);
        modelAndView.addObject("exhibitor", exhibitorManagerService.loadExhibitorByEid(eid));
        modelAndView.addObject("term", exhibitorManagerService.getExhibitorTermByEid(eid));
        modelAndView.addObject("booth", exhibitorManagerService.queryBoothByEid(eid));
        modelAndView.addObject("currentTerm", exhibitorManagerService.queryCurrentTermNumber());
        modelAndView.addObject("exhibitorInfo", exhibitorManagerService.loadExhibitorInfoByEid(eid));
        /*石材展需求开始*/
//        modelAndView.addObject("exhibitorMeipai", meipaiService.getMeiPaiByEid(eid));
        /*石材展需求结束*/
        modelAndView.addObject("invoice", invoiceService.getByEid(eid));
        return modelAndView;
    }
    
    /**
     * 添加展商账号
     * @param request
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addExhibitor", method = RequestMethod.POST)
    public BaseResponse addExhibitorAccount(@ModelAttribute AddExhibitorRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	exhibitorManagerService.addExhibitor(request, principle.getAdmin().getId());
        } catch (DuplicateUsernameException e) {
            response.setResultCode(2);
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            log.error("add exhibitor account error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 修改展商账号
     * @param request
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyExhibitor", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorAccount(@ModelAttribute ModifyExhibitorRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            exhibitorManagerService.modifyExhibitorAccount(request, principle.getAdmin().getId());
        } catch (DuplicateUsernameException e) {
            response.setResultCode(2);
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            log.error("modify exhibitor account error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "activeExhibitor", method = RequestMethod.POST)
    public BaseResponse activeExhibitor(@ModelAttribute ActiveExhibitorRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            TExhibitorTerm term = new TExhibitorTerm();
            term.setEid(request.getEid());
            if (request.getId() != null) {
                term.setId(request.getId());
                term.setUpdateUser(principle.getAdmin().getId());
            } else {
                term.setCreateUser(principle.getAdmin().getId());
            }
            term.setCreateTime(new Date());
            //set base data
            term.setBoothNumber(request.getBoothNumber());
            term.setJoinTerm(request.getTerm());
            term.setIsDelete(0);
            term.setMark(request.getMark());
            exhibitorManagerService.activeExhibitor(term);
        } catch (Exception e) {
            log.error("active exhibitor error.", e);
            response.setResultCode(1);
        }
        return response;
    }

    /**
     * 绑定展位号
     * @param request
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "bindBooth", method = RequestMethod.POST)
    public BaseResponse bindBooth(@ModelAttribute BindBoothRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            TExhibitorBooth booth = new TExhibitorBooth();
            if (request.getId() != null) {
                booth.setId(request.getId());
                booth.setUpdateUser(principle.getAdmin().getId());
                booth.setUpdateTime(new Date());
            } else {
                booth.setCreateTime(new Date());
                booth.setCreateUser(principle.getAdmin().getId());
            }
            booth.setEid(request.getEid());
            //set base data
            booth.setBoothNumber(request.getBoothNumber().trim());
            if(request.getExhibitionArea() == null || "".equals(request.getExhibitionArea())){
            	booth.setExhibitionArea(request.getBoothNumber().trim().substring(0,2) + "厅");
            }else{
                if(request.getExhibitionArea().contains("厅")) booth.setExhibitionArea(request.getExhibitionArea());
                else  booth.setExhibitionArea(request.getExhibitionArea() + "厅");
            }
            booth.setMark(request.getMark());
            exhibitorManagerService.bindBoothInfo(booth);
        } catch (Exception e) {
            log.error("bind exhibitor booth number error.", e);
            response.setResultCode(1);
        }
        return response;
    }

    /**
     * 批量修改所属人
     * @param eids
     * @param tag
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyExhibitorsTag", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorsTag(@RequestParam(value = "eids", defaultValue = "") Integer[] eids, @RequestParam("tag") Integer tag, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eids != null && tag != null){
        		exhibitorManagerService.modifyExhibitorsTag(eids, tag, principle.getAdmin().getId());
        	}
        } catch (Exception e) {
            log.error("modify exhibitors tag error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 批量修改展团
     * @param eids
     * @param group
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyExhibitorsGroup", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorsGroup(@RequestParam(value = "eids", defaultValue = "") Integer[] eids,
    										  @RequestParam("group") Integer group, 
    										  @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eids != null && group != null){
        		exhibitorManagerService.modifyExhibitorsGroup(eids, group, principle.getAdmin().getId());
        	}
        } catch (Exception e) {
            log.error("modify exhibitors group error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 批量修改展区
     * @param eids
     * @param area
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyExhibitorsArea", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorsArea(@RequestParam(value = "eids", defaultValue = "") Integer[] eids, @RequestParam("area") Integer area, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eids != null && area != null){
        		exhibitorManagerService.modifyExhibitorsArea(eids, area, principle.getAdmin().getId());
        	}
        } catch (Exception e) {
            log.error("modify exhibitors area error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 批量注销展商
     * @param eids
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "disableExhibitors", method = RequestMethod.POST)
    public BaseResponse disableExhibitors(@RequestParam(value = "eids", defaultValue = "") Integer[] eids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eids != null){
        		exhibitorManagerService.disableExhibitors(eids, principle.getAdmin().getId());
        	}
        } catch (Exception e) {
            log.error("disable exhibitor error.", e);
            response.setResultCode(1);
        }
        return response;
    }

    /**
     * 批量启用展商
     * @param eids
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "enableExhibitors", method = RequestMethod.POST)
    public BaseResponse enableExhibitors(@RequestParam(value = "eids", defaultValue = "") Integer[] eids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eids != null){
        		exhibitorManagerService.enableExhibitor(eids, principle.getAdmin().getId());
        	}
        } catch (Exception e) {
            log.error("enable exhibitor error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 批量删除展商
     * @param eids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteExhibitors", method = RequestMethod.POST)
    public BaseResponse deleteExhibitors(@RequestParam(value = "eids", defaultValue = "") Integer[] eids) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eids == null) throw new Exception();
        	exhibitorManagerService.deleteExhibitorByEids(eids);
        } catch (Exception e) {
        	log.error("delete exhibitors error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 查询所有国家
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAllCountry", method = RequestMethod.POST)
    public List<WCountry> queryAllCountry(@ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
    	List<WCountry> country = new ArrayList<WCountry>();
    	try {
        	country = countryProvinceService.loadAllCountry();
        } catch (Exception e) {
            log.error("query country error.", e);
        }
        return country;
    }
    
    /**
     * 查询所有省份
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAllProvince", method = RequestMethod.POST)
    public List<WProvince> queryAllProvince(@ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
    	List<WProvince> province = new ArrayList<WProvince>();
    	try {
    		province = countryProvinceService.loadAllProvince();
        } catch (Exception e) {
            log.error("query province error.", e);
        }
        return province;
    }
    
    /**
     * 通过countryId查询省份
     * @param countryId
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryProvinceByCountryId", method = RequestMethod.POST)
    public List<WProvince> queryProvinceByCountryId(@RequestParam("countryId") Integer countryId, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
    	List<WProvince> province = new ArrayList<WProvince>();
        try {
        	province = countryProvinceService.loadProvinceByCountryId(countryId);
        } catch (Exception e) {
            log.error("query province by countryId error.", e);
        }
        return province;
    }
    
    /**
     * 修改展商基本信息
     * @param request
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyExhibitorInfo", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorInfo(@ModelAttribute ModifyExhibitorInfoRequest request, 
								    		@RequestParam("eid") Integer eid,
								    		@ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(eid != null){
        		exhibitorManagerService.modifyExhibitorInfo(request, eid, principle.getAdmin().getId());
        	}else{
        		throw new Exception();
        	}
        } catch (Exception e) {
            log.error("modify exhibitor info error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 上传Logo并修改展商基本信息
     * @param logoFile
     * @param request
     * @param eid
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value="upload/modifyInfo", method={RequestMethod.POST,RequestMethod.GET})
    public BaseResponse uploadModifyInfo(@RequestParam MultipartFile logoFile,
						    		  	 @ModelAttribute ModifyExhibitorInfoRequest request, 
						    		  	 @RequestParam("eid") Integer eid,
						    		  	 @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle){
    	BaseResponse response = new BaseResponse();
        try {
        	if(eid != null){
        		String oldLogoPath = exhibitorManagerService.loadExhibitorInfoByEid(eid).getLogo();
        		File logo = importExportAction.upload(logoFile, null, null);
        		if(StringUtils.isNotEmpty(oldLogoPath)){
        			File oldLogo = new File(oldLogoPath);
        			if(oldLogo.exists() == false) FileUtils.deleteQuietly(oldLogo);
        		}
        		request.setLogo(logo.getPath());
        		modifyExhibitorInfo(request, eid, principle);
        	}else{
        		throw new Exception();
        	}
        } catch (Exception e) {
            log.error("modify exhibitor info error.", e);
            response.setResultCode(1);
        }
        return response;
    }

    /**
     * 显示Logo
     * @param response
     * @param eid
     */
    @RequestMapping(value = "showLogo", method = RequestMethod.GET)
    public void showLogo(HttpServletResponse response, @RequestParam("eid") Integer eid) {
        try {
            String logoFileName = exhibitorManagerService.loadExhibitorInfoByEid(eid).getLogo();
            if (StringUtils.isNotEmpty(logoFileName)) {
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
    
}
