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
 * Created by wujianbin on 2014-04-24.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class ContactAction extends BaseAction {

    private static Logger log = Logger.getLogger(ContactAction.class);

    @Autowired
    private ContactManagerService contactService;
    @Autowired
    private ExhibitorManagerService exhibitorService;
    @Autowired
    private MsgService msgService;
    @Autowired
    private FreeMarkerConfigurer freeMarker;// 注入FreeMarker模版封装框架
    
    @ResponseBody
    @RequestMapping(value = "queryContacts", method = RequestMethod.POST)
    public QueryContactsResponse queryContacts(@ModelAttribute QueryContactsRequest request) {
    	QueryContactsResponse response = null;
        try {
        	response = contactService.queryContacts(request, request.getEid());
        } catch (Exception e) {
            response = new QueryContactsResponse();
            log.error("query contacts error.", e);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    public BaseResponse addContact(@ModelAttribute AddContactRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TContact contact = new TContact();
        	contact.setEid(request.getEid());
        	contact.setName(request.getName());
        	contact.setPosition(request.getPosition());
        	contact.setPhone(request.getPhone());
        	contact.setEmail(request.getEmail());
        	contact.setAddress(request.getAddress());
        	contactService.addContact(contact);
        } catch (Exception e) {
        	log.error("add contact error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "addExpressNumber", method = RequestMethod.POST)
    public BaseResponse addExpressNumber(@RequestParam("eid") Integer eid, @ModelAttribute AddExpressNumberRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TContact contact = contactService.loadContactById(request.getId());
        	contact.setExpressNumber(request.getExpressNumber());
        	contactService.modifyContact(contact);
        	TExhibitorMsg msg = new TExhibitorMsg();
        	msg.setEid(eid);
        	msg.setTitle("快递通知");
        	msg.setContent(getExpressText(request.getExpressNumber()).substring(1,getExpressText(request.getExpressNumber()).length()));
        	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	msg.setAdminUser(principle.getAdmin().getId());
            }
        	msg.setSendTime(new Date());
        	msg.setIsRead(0);
        	msg.setIsDelete(0);
        	msgService.sendMsg(msg);
        } catch (Exception e) {
        	log.error("add express number error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    /**
	 * 通过模板构造邮件内容，参数expressNumber将替换模板文件中的${expressNumber}标签。
	 */
	private String getExpressText(String expressNumber) throws Exception {
		// 通过指定模板名获取FreeMarker模板实例
		Template template = freeMarker.getConfiguration().getTemplate("express/express.ftl");
		
		// FreeMarker通过Map传递动态数据
		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("expressNumber", expressNumber); // 注意动态数据的key和模板标签中指定的属性相匹配
		
		// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		return htmlText;
	}
    
    @ResponseBody
    @RequestMapping(value = "modifyContact", method = RequestMethod.POST)
    public BaseResponse modifyContact(@ModelAttribute ModifyContactRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TContact contact = contactService.loadContactById(request.getId());
        	contact.setEid(request.getEid());
        	contact.setName(request.getName());
        	contact.setPosition(request.getPosition());
        	contact.setPhone(request.getPhone());
        	contact.setEmail(request.getEmail());
        	contact.setAddress(request.getAddress());
        	contactService.modifyContact(contact);
        } catch (Exception e) {
        	log.error("modify contact error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "removeContacts", method = RequestMethod.POST)
    public BaseResponse removeContacts(@RequestParam(value = "cids", defaultValue = "") Integer[] cids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(cids == null) throw new Exception();
        	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
        		contactService.removeContactByCIds(cids);
            }
        } catch (Exception e) {
        	log.error("remove contacts error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "addToJoiners", method = RequestMethod.POST)
    public BaseResponse addToJoiners(@RequestParam(value = "cids", defaultValue = "") Integer[] cids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(cids == null) throw new Exception();
        	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
        		contactService.addToJoiners(cids,principle.getAdmin().getId());
            }
        } catch (Exception e) {
        	log.error("remove contacts error.", e);
            response.setResultCode(1);
        }
        return response;
    }
}
