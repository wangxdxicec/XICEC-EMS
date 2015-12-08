package com.zhenhappy.ems.manager.action.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.entity.TContact;
import com.zhenhappy.ems.entity.TExhibitorMsg;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.AddContactRequest;
import com.zhenhappy.ems.manager.dto.AddExpressNumberRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyContactRequest;
import com.zhenhappy.ems.manager.dto.QueryContactsRequest;
import com.zhenhappy.ems.manager.dto.QueryContactsResponse;
import com.zhenhappy.ems.manager.dto.SendMsgRequest;
import com.zhenhappy.ems.manager.service.ContactManagerService;
import com.zhenhappy.ems.manager.service.ExhibitorManagerService;
import com.zhenhappy.ems.service.MsgService;

import freemarker.template.Template;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Created by wujianbin on 2014-09-04.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class MsgAction extends BaseAction {

    private static Logger log = Logger.getLogger(MsgAction.class);

    @Autowired
    private MsgService msgService;
    
    @ResponseBody
    @RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    public BaseResponse sendMsg(@RequestParam("eid") Integer eid, @ModelAttribute SendMsgRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TExhibitorMsg msg = new TExhibitorMsg();
        	msg.setEid(request.getEid());
        	msg.setTitle(request.getTitle());
        	msg.setContent(request.getContent());
        	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	msg.setAdminUser(principle.getAdmin().getId());
            }
        	msg.setSendTime(new Date());
        	msg.setIsRead(0);
        	msg.setIsDelete(0);
        	msgService.sendMsg(msg);
        } catch (Exception e) {
        	log.error("add send message error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
}
