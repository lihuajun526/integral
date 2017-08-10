package com.operational.platform.vip.filter;

import com.operational.platform.dbservice.model.User;
import com.operational.platform.dbservice.service.UserService;
import com.operational.platform.vip.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String vipAccessToken = request.getParameter("vipAccessToken");

        if (url.indexOf("/v_login") == -1) {//该请求不需要登录
            chain.doFilter(request, response);
            return;
        }
        if (StringUtils.isEmpty(vipAccessToken)) {//vipAccessToken参数未传
            request.getRequestDispatcher("/user/notoken").forward(request, response);
            return;
        }

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
        UserService userService = (UserService) webApplicationContext.getBean("userService");
        User loginUser = null;
        if (Constant.SessionMap.get(vipAccessToken) == null) {
            loginUser = userService.getByAccessToken(vipAccessToken);
            if (loginUser == null) {
                request.getRequestDispatcher("/user/nologin").forward(request, response);
                return;
            } else {
                Constant.SessionMap.put(vipAccessToken, loginUser);
            }
        }
        if (loginUser.getVipAccessTokenExpires().getTime() <= System.currentTimeMillis()) {//vipAccessToken过期
            request.getRequestDispatcher("/user/nologin").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

}