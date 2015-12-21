package com.zhenhappy.ems.action;

import com.zhenhappy.ems.dto.ExhibitorBooth;
import com.zhenhappy.ems.dto.LoginRequest;
import com.zhenhappy.ems.dto.LoginResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.service.ExhibitorService;
import com.zhenhappy.ems.sys.Constants;
import com.zhenhappy.ems.util.ImageMarkLogoByText;
import com.zhenhappy.system.SystemConfig;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.Locale;

/**
 * Created by lianghaijian on 2014-03-31.
 */
@Controller
@RequestMapping(value = "/")
public class PublicAction {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private ExhibitorService exhibitorService;

    private static Logger log = Logger.getLogger(PublicAction.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        if ("le".equals(request.getParameter("locale"))) {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        } else {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
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
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest, Locale locale) {
        LoginResponse response = new LoginResponse();
        try {
            TExhibitor exhibitor = exhibitorService.getExhibitorByUsernamePassword(request.getUsername(), request.getPassword());
            if (exhibitor == null || exhibitor.getIsLogout().intValue() == 1) {
                response.setResultCode(1);
            } else {
                ExhibitorBooth booth = exhibitorService.loadBoothInfo(exhibitor.getEid());
                httpServletRequest.getSession().setAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE, new Principle(exhibitor));
                httpServletRequest.getSession().setAttribute("boothInfo", booth);
                if (exhibitor.getArea() != null) {
                    httpServletRequest.getSession().setAttribute("zone", exhibitor.getArea());
                } else {
                    //默认国内展区
                    httpServletRequest.getSession().setAttribute("zone", new Integer(1));
                }
                response.setResultCode(0);
            }
        } catch (Exception e) {
            log.error("login error.username:" + request.getUsername(), e);
            response.setResultCode(3);
        }
        return response;
    }

    @RequestMapping(value = "changeLanguage", method = RequestMethod.GET)
    public void changeLanguage(HttpServletRequest request, HttpServletResponse response, @RequestParam("locale") String locale) {
        String refer = request.getHeader("referer");
        if (locale.equals("us")) {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        } else {
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
        }
        try {
            response.sendRedirect(refer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
        response.sendRedirect(request.getContextPath());
    }

    @RequestMapping(value = "banner")
    public void bannerPrint(@RequestParam("eid") Integer eid, @RequestParam("num") Integer num, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        String boothNumber = exhibitorService.loadBoothNum(eid);
        String fileName = systemConfig.getVal(Constants.appendix_directory) + "/banners/" + eid + "_" + boothNumber + "_" + num + ".jpg";
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream inputStream = this.getClass().getResourceAsStream("/banner/banner-" + num + ".jpg");
            if (num.intValue() == 1) {
                ImageMarkLogoByText.markByText(boothNumber, inputStream, file.getAbsolutePath(), new Color(0,91,172,255), 190, 130, 18, 0);
            }
            if (num.intValue() == 2) {
                ImageMarkLogoByText.markByText(boothNumber, inputStream, file.getAbsolutePath(), new Color(0,91,172,255), 400, 359, 21, 0);
            }
            if (num.intValue() == 3) {
                ImageMarkLogoByText.markByText(boothNumber, inputStream, file.getAbsolutePath(), new Color(0,91,172,255), 165, 128, 16, 1);
            }
            if (num.intValue() == 4) {
                ImageMarkLogoByText.markByText(boothNumber, inputStream, file.getAbsolutePath(), new Color(0,91,172,255), 194, 372, 19, 0);
            }
        }
        if (file.exists()) {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                inputStream = new FileInputStream(file);
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("contact")
    public String contactUs(){
        return "/public/contact";
    }

    /**
     * findPassword method
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findPassword")
    public String findPassword( @RequestParam("company") String company, @RequestParam("username") String username) {
        if (StringUtils.isNotEmpty(company) && StringUtils.isNotEmpty(username)) {
            String password = "";
            try {
                password = (String) jdbcTemplate.queryForObject("select password from [t_exhibitor] where company = ?", new Object[]{company}, java.lang.String.class);
            } catch (Exception e) {
                return "company";
            }
            try {
                password = (String) jdbcTemplate.queryForObject("select password from [t_exhibitor] where username = ?", new Object[]{username}, java.lang.String.class);
            } catch (Exception e) {
                return "username";
            }
            try {
                password = (String) jdbcTemplate.queryForObject("select password from [t_exhibitor] where company = ? and username = ?", new Object[]{company, username}, java.lang.String.class);
            } catch (Exception e) {
                return "all";
            }
            return password;
        } else return "";
    }

    /**
     * findPassword method
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findPasswordEn")
    public String findPasswordEn (@RequestParam("username") String username, @RequestParam("booth") String booth){
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(booth)) {
            Integer eid = 0;
            try {
                eid = (Integer) jdbcTemplate.queryForObject("select eid from [t_exhibitor_booth] where booth_number = ?", new Object[]{booth}, java.lang.Integer.class);
            } catch (Exception e) {
                return "booth";
            }
            String password = "";
            try {
                password = (String) jdbcTemplate.queryForObject("select password from [t_exhibitor] where username = ?", new Object[]{username}, java.lang.String.class);
            } catch (Exception e) {
                return "username";
            }
            try {
                password = (String) jdbcTemplate.queryForObject("select password from [t_exhibitor] where eid = ? and username = ?", new Object[]{eid, username}, java.lang.String.class);
            } catch (Exception e) {
                return "all";
            }
            return password;
        } else return "";
    }
}
