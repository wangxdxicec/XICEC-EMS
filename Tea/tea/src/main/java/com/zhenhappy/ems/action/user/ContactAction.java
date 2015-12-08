package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.dto.QueryContactResponse;
import com.zhenhappy.ems.entity.TContact;
import com.zhenhappy.ems.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wujianbin on 2014-05-15.
 */
@Controller
@RequestMapping(value = "/user/contact")
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
public class ContactAction extends BaseAction {

    private static Logger log = Logger.getLogger(ContactAction.class);
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        ModelAndView modelAndView = new ModelAndView("/user/contact/index");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public QueryContactResponse list(@ModelAttribute(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        QueryContactResponse response = new QueryContactResponse();
        try {
            List<TContact> contacts = contactService.queryContactsByEid(principle.getExhibitor().getEid());
            response.setContacts(contacts);
            response.setResultCode(0);
        } catch (Exception e) {
            response.setResultCode(1);
            log.error("list contacts error.", e);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse save(@ModelAttribute TContact contact, @ModelAttribute(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            if(contact.getId()==null){
                contact.setIsDelete(0);
                contact.setEid(principle.getExhibitor().getEid());
            }
            contactService.saveContact(contact);
            response.setResultCode(0);
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResponse delete(@RequestParam("cid") Integer cid, @ModelAttribute(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            contactService.deleteContact(cid);
            response.setResultCode(0);
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }
}
