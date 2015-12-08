package com.zhenhappy.ems.action;

import com.zhenhappy.ems.dto.ExhibitorBooth;
import com.zhenhappy.ems.dto.LoginRequest;
import com.zhenhappy.ems.dto.LoginResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.service.ExhibitorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by wujianbin on 2014-03-31.
 */
@Controller
@RequestMapping(value = "/")
public class PublicAction {

    @Autowired
    private ExhibitorService exhibitorService;

    private static Logger log = Logger.getLogger(PublicAction.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        if("le".equals(request.getParameter("locale"))){
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,Locale.US);
        }else{
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,Locale.CHINA);
        }
        return "/public/login";
    }

    /**
     * login method
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest,Locale locale) {
        LoginResponse response = new LoginResponse();
        try {
            TExhibitor exhibitor = exhibitorService.getExhibitorByUsernamePassword(request.getUsername(), request.getPassword());
            if (exhibitor == null||exhibitor.getIsLogout().intValue()==1) {
                response.setResultCode(1);
            } else {
                ExhibitorBooth booth = exhibitorService.loadBoothInfo(exhibitor.getEid());
                httpServletRequest.getSession().setAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE, new Principle(exhibitor));
                httpServletRequest.getSession().setAttribute("boothInfo", booth);
                response.setResultCode(0);
            }
            if(request.getArea() != null) httpServletRequest.getSession().setAttribute("zone",request.getArea());
            else httpServletRequest.getSession().setAttribute("zone", 1);
        } catch (Exception e) {
            log.error("login error.username:" + request.getUsername(), e);
            response.setResultCode(3);
        }
        return response;
    }

    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
        response.sendRedirect(request.getContextPath());
    }
}
