package com.zhenhappy.ems.action.user;

import com.alibaba.fastjson.JSONArray;
import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.dto.GetMailSendDetailsResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.email.Email;
import com.zhenhappy.ems.entity.TEmailSendDetail;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.ems.service.MailService;
import com.zhenhappy.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lianghaijian on 2014-08-12.
 */
@Controller
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
@RequestMapping(value = "user")
public class MailAction {

    private static Logger log = Logger.getLogger(MailAction.class);

    @Autowired
    private ExhibitorService exhibitorService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "sendMail", method = RequestMethod.GET)
    public String sendMail() {
        return "/user/mail/mails";
    }

    @RequestMapping(value = "sendMail", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse sendMail(@RequestParam(value = "context") String context, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse baseResponse = new BaseResponse();
        try {

            List<Email> emails = JSONArray.parseArray(context, Email.class);
            String booth = exhibitorService.loadBoothNum(principle.getExhibitor().getEid());
            String company = exhibitorService.getExhibitorByEid(principle.getExhibitor().getEid()).getCompany();
            String companye = exhibitorService.getExhibitorByEid(principle.getExhibitor().getEid()).getCompanye();
            for (Email email : emails) {
                if (email.getFlag() == 1) {
                    email.setSubject(company + "邀请函");
                    email.setBoothNumber(booth);
                    email.setCompany(principle.getExhibitor().getCompany());
                } else {
                    email.setSubject("The invitation Of " + companye);
                    email.setBoothNumber(booth);
                    email.setCompany(principle.getExhibitor().getCompanye());
                }
                mailService.sendMailByAsynchronousMode(email, principle.getExhibitor().getEid());
            }
        } catch (Exception e) {
            baseResponse.setResultCode(1);
        }
        return baseResponse;
    }

    @RequestMapping(value = "retrySendMail")
    @ResponseBody
    public BaseResponse retrySendMail(@RequestParam(value = "mid") Integer mid, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            Email email = new Email();
            TEmailSendDetail sendDetail = mailService.loadMailByMid(mid, principle.getExhibitor().getEid());
            if(sendDetail==null){
                throw new Exception("Mail can not found");
            }
            String company = exhibitorService.getExhibitorByEid(principle.getExhibitor().getEid()).getCompany();
            String companye = exhibitorService.getExhibitorByEid(principle.getExhibitor().getEid()).getCompanye();
            email.setFlag(sendDetail.getLanguage());
            if (email.getFlag() == 1) {
                email.setSubject(company + "邀请函");
                email.setCompany(principle.getExhibitor().getCompany());
            } else {
                email.setSubject("The invitation Of " + companye);
                email.setCompany(principle.getExhibitor().getCompanye());
            }
            email.setGender(sendDetail.getGender());
            email.setName(sendDetail.getCompanyName());
            email.setReceivers(sendDetail.getAddress());
            mailService.retrySendMailByAsynchronousMode(email,sendDetail.getId());
        } catch (Exception e) {
            baseResponse.setResultCode(1);
        }
        return baseResponse;
    }

    @RequestMapping(value = "mails", method = RequestMethod.GET)
    @ResponseBody
    public GetMailSendDetailsResponse getMailSendDetail(@ModelAttribute Page page, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        GetMailSendDetailsResponse response = new GetMailSendDetailsResponse();
        try {
            if (page == null) {
                page = new Page();
                page.setPageIndex(1);
                page.setPageSize(10);
            }
            mailService.loadDetailByEid(page, principle.getExhibitor().getEid());
            BeanUtils.copyProperties(page, response);
            response.setDatas(page.getRows());
        } catch (Exception e) {
            log.error("get mails error.", e);
            response.setResultCode(1);
        }
        return response;
    }

    @RequestMapping(value = "previewMail", method = RequestMethod.POST)
    public ModelAndView previewMail(@ModelAttribute("mail") Email email, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        ModelAndView modelAndView = new ModelAndView();
        String booth = exhibitorService.loadBoothNum(principle.getExhibitor().getEid());
        if (email.getFlag() == 1) {
            modelAndView.setViewName("/user/mail/mailTemplate1");
            email.setSubject("厦门国际石材展邀请函");
            email.setBoothNumber(booth);
            email.setCompany(principle.getExhibitor().getCompany());
        } else {
            modelAndView.setViewName("/user/mail/mailTemplate2");
            email.setSubject("The invitation Of China Xiamen International Stone Fair");
            email.setBoothNumber(booth);
            email.setCompany(principle.getExhibitor().getCompanye());
        }
        modelAndView.addObject("email", email);
        return modelAndView;
    }

}
