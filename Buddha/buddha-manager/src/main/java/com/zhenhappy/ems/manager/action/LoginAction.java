package com.zhenhappy.ems.manager.action;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.entity.TAdminUser;
import com.zhenhappy.ems.manager.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wujianbin on 2014-04-22.
 */
@Controller
@RequestMapping(value = "/")
@SessionAttributes(ManagerPrinciple.MANAGERPRINCIPLE)
public class LoginAction extends BaseAction {

    private static Logger log = Logger.getLogger(LoginAction.class);

    @Autowired
    private AdminService adminService;

    /**
     * process login.
     * <p/>
     * if login success,put principle into session.
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            TAdminUser admin = adminService.login(username, password);
            if (admin != null) {
                baseResponse.setResultCode(0);
                ManagerPrinciple principle = new ManagerPrinciple();
                principle.setAdmin(admin);
                request.getSession().setAttribute(ManagerPrinciple.MANAGERPRINCIPLE, principle);
            } else {
                baseResponse.setResultCode(1);
            }
        } catch (Exception e) {
            log.error("login error.", e);
            baseResponse.setResultCode(1);
        }
        return baseResponse;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "public/login";
    }

    /**
     * logout and remove principle from session.
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(ManagerPrinciple.MANAGERPRINCIPLE);
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
