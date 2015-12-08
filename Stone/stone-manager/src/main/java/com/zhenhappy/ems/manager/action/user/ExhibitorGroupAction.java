package com.zhenhappy.ems.manager.action.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.AddExhibitorGroupRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyExhibitorGroupRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorGroupRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorGroupResponse;
import com.zhenhappy.ems.manager.exception.DuplicateUsernameException;
import com.zhenhappy.ems.manager.service.ExhibitorGroupManagerService;

/**
 * Created by wujianbin on 2014-09-03.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class ExhibitorGroupAction extends BaseAction {

	private static Logger log = Logger.getLogger(ExhibitorGroupAction.class);

    @Autowired
    private ExhibitorGroupManagerService exhibitorGroupManagerService;
    
    @RequestMapping(value = "exhibitorGroup")
    public ModelAndView directToExhibitorGroup() {
        ModelAndView modelAndView = new ModelAndView("user/group/group");
        return modelAndView;
    }
    
    /**
     * 分页查询展团列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryExhibitorGroupByPage")
    public QueryExhibitorGroupResponse queryExhibitorGroupByPage(@ModelAttribute QueryExhibitorGroupRequest request) {
    	QueryExhibitorGroupResponse response = null;
        try {
        	response = exhibitorGroupManagerService.queryExhibitorGroupByPage(request);
        } catch (Exception e) {
            response.setResultCode(1);
            log.error("query exhibitor group error.", e);
        }
        return response;
    }
    
    /**
     * 添加展团账号
     * @param request
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addExhibitorGroup", method = RequestMethod.POST)
    public BaseResponse addExhibitorGroup(@ModelAttribute AddExhibitorGroupRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	exhibitorGroupManagerService.addExhibitorGroup(request, principle.getAdmin().getId());
        } catch (DuplicateUsernameException e) {
            response.setResultCode(2);
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            log.error("add exhibitor group error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 修改展团账号
     * @param request
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modifyExhibitorGroup", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorGroup(@ModelAttribute ModifyExhibitorGroupRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	exhibitorGroupManagerService.modifyExhibitorGroup(request, principle.getAdmin().getId());
        } catch (DuplicateUsernameException e) {
            response.setResultCode(2);
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            log.error("modify exhibitor group error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
     * 批量删除展团账号
     * @param ids
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteExhibitorGroups", method = RequestMethod.POST)
    public BaseResponse deleteExhibitorGroups(@RequestParam(value = "ids", defaultValue = "") Integer[] ids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(ids == null) throw new Exception();
        	exhibitorGroupManagerService.deleteExhibitorGroups(ids);
        } catch (Exception e) {
        	log.error("remove exhibitor groups error.", e);
            response.setResultCode(1);
        }
        return response;
    }

}
