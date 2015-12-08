package com.zhenhappy.ems.manager.security;

import com.zhenhappy.ems.manager.dto.ManagerPrinciple;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujianbin on 2014-04-27.
 */
public class LoginFilter implements Filter {
	List<String> whiteList = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
		whiteList.add("/manager/user/exportBoothInfoToExcel_1");
		whiteList.add("/manager/user/exportBoothInfoToExcel_2");
		whiteList.add("/manager/user/showProductImg");
        whiteList.add("/manager/user/showLogo");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ManagerPrinciple principle = (ManagerPrinciple) request.getSession().getAttribute(ManagerPrinciple.MANAGERPRINCIPLE);
        if (principle == null) {
            if(inWhiteList(request.getRequestURI())){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

	/**
	 * 白名单匹配
	 * @param keyword
	 * @return
	 */
	private boolean inWhiteList(String keyword){
		for(String white : whiteList) if(white.contains(keyword)) return true;
		return false;
	}

    @Override
    public void destroy() {

    }
}
