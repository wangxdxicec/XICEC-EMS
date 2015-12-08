package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.dto.GetMessagesResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.entity.TExhibitorMsg;
import com.zhenhappy.ems.service.MsgService;
import com.zhenhappy.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wujianbin on 2014-08-28.
 */
@Controller
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
@RequestMapping("/user")
public class MessageAction extends BaseAction {

    @Autowired
    private MsgService msgService;

    private static Logger log = Logger.getLogger(MessageAction.class);

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    @ResponseBody
    public GetMessagesResponse loadMessages(@ModelAttribute("page") Page page, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        GetMessagesResponse messagesResponse = new GetMessagesResponse();
        try{
            List<TExhibitorMsg> msgs = msgService.loadMessageByEid(principle.getExhibitor().getEid(),null,0,page);
            BeanUtils.copyProperties(page,messagesResponse);
            messagesResponse.setDatas(msgs);
        }catch (Exception e){
            log.error("load messages Error.",e);
            messagesResponse.setResultCode(1);
        }
        return messagesResponse;
    }

    @RequestMapping(value = "/message/read")
    @ResponseBody
    public BaseResponse readMessage(@RequestParam("messageId") Integer messageId, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            msgService.readMessage(principle.getExhibitor().getEid(), messageId);
            response.setResultCode(0);
        } catch (Exception e) {
            log.error("read message Error.",e);
            response.setResultCode(1);
        }
        return response;
    }

    @RequestMapping(value = "/message/delete")
    @ResponseBody
    public BaseResponse deleteMessage(@RequestParam("messageId") Integer messageId, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            msgService.deleteMessage(principle.getExhibitor().getEid(), messageId);
            response.setResultCode(0);
        } catch (Exception e) {
            log.error("delete message Error.",e);
            response.setResultCode(1);
        }
        return response;
    }
}
