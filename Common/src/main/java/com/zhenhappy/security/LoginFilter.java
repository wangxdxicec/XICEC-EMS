package com.zhenhappy.security;

import com.zhenhappy.ems.dto.Principle;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * login filter
 * Created by wujianbin on 2014-04-13.
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Principle principle = (Principle) request.getSession().getAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE);
        if (principle == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath()+"/");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
