package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.dto.Principle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Kristine-Mac on 15/7/9.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
public class WeixinAction {

    @RequestMapping(value = "/weixin/index")
    public String index() {
        return "/user/weixin/index";
    }
}
