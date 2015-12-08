package com.zhenhappy.ems.manager.action.user;

import java.util.ArrayList;
import java.util.Date;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.AddExhibitorJoinerRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyExhibitorJoinerRequest;
import com.zhenhappy.ems.manager.dto.QueryJoinersRequest;
import com.zhenhappy.ems.manager.dto.QueryJoinersResponse;
import com.zhenhappy.ems.manager.service.ExhibitorManagerService;
import com.zhenhappy.ems.manager.service.JoinerManagerService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by wujianbin on 2014-04-24.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class JoinerAction extends BaseAction {

    private static Logger logger = Logger.getLogger(JoinerAction.class);

    @Autowired
    private JoinerManagerService joinerManagerService;

    @Autowired
    private ExhibitorManagerService exhibitorService;

    @ResponseBody
    @RequestMapping(value = "queryJoiners", method = RequestMethod.POST)
    public QueryJoinersResponse queryJoiners(@ModelAttribute QueryJoinersRequest request) {
        QueryJoinersResponse response = null;
        try {
            TExhibitorInfo exhibitorInfo = exhibitorService.loadExhibitorInfoByEid(request.getEid());
            if (exhibitorInfo != null) {
                response = joinerManagerService.queryJoiners(request, exhibitorInfo.getEinfoid());
            } else {
                response = new QueryJoinersResponse();
                response.setRows(new ArrayList());
            }
        } catch (Exception e) {
            response = new QueryJoinersResponse();
            logger.error("query exhibitor joiners error.", e);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "addExhibitorJoiner", method = RequestMethod.POST)
    public BaseResponse addExhibitorJoiner(@ModelAttribute AddExhibitorJoinerRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TExhibitorJoiner exhibitorJoiner = new TExhibitorJoiner();
        	exhibitorJoiner.setEid(request.getEid());
        	exhibitorJoiner.setName(request.getName());
        	exhibitorJoiner.setPosition(request.getPosition());
        	exhibitorJoiner.setTelphone(request.getTelphone());
        	exhibitorJoiner.setEmail(request.getEmail());
        	exhibitorJoiner.setCreateTime(new Date());
            if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	exhibitorJoiner.setAdmin(principle.getAdmin().getId());
            }
            joinerManagerService.addExhibitorJoiner(exhibitorJoiner);
        } catch (Exception e) {
        	logger.error("add exhibitor joiner error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "modifyExhibitorJoiner", method = RequestMethod.POST)
    public BaseResponse modifyExhibitorJoiner(@ModelAttribute ModifyExhibitorJoinerRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TExhibitorJoiner exhibitorJoiner = joinerManagerService.loadExhibitorJoinerById(request.getId());
        	exhibitorJoiner.setEid(request.getEid());
        	exhibitorJoiner.setName(request.getName());
        	exhibitorJoiner.setPosition(request.getPosition());
        	exhibitorJoiner.setTelphone(request.getTelphone());
        	exhibitorJoiner.setEmail(request.getEmail());
        	exhibitorJoiner.setCreateTime(new Date());
            if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	exhibitorJoiner.setAdmin(principle.getAdmin().getId());
            	exhibitorJoiner.setAdminUpdateTime(new Date());
            }
            joinerManagerService.modifyExhibitorJoiner(exhibitorJoiner);
        } catch (Exception e) {
        	logger.error("modify exhibitor joiner error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "removeJoiners", method = RequestMethod.POST)
    public BaseResponse removeJoiners(@RequestParam(value = "jids", defaultValue = "") Integer[] jids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(jids == null) throw new Exception();
        	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
        		joinerManagerService.removeJoinerByJIds(jids, principle.getAdmin().getId());
            }
        } catch (Exception e) {
        	logger.error("remove exhibitor joiners error.", e);
            response.setResultCode(1);
        }
        return response;
    }
}
