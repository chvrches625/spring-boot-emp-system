package com.jiangzhe.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //得到LoginController中传入的登录用户，判断是否有session
        Object user = request.getSession().getAttribute("loginUser");
        //如果已有session，不操作，如果没有session，需要重新登录
        if (user == null) {
            //未登录
            request.setAttribute("msg","没有权限请先登录");
            //转发到登录页面
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            //已登录
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
