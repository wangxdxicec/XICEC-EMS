package com.zhenhappy.ems.manager.action.user;

import com.zhenhappy.ems.manager.action.BaseAction;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wujianbin on 2014-04-23.
 */
@Controller
@RequestMapping(value = "user")
public class MainAction extends BaseAction {

    private static Logger log = Logger.getLogger(MainAction.class);

    /**
     * login success, redirect to main page.
     *
     * @return
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("user/main/main");
        return modelAndView;
    }
}
