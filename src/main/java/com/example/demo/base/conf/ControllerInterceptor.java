package com.example.demo.base.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @ClassName logInterceptor
 * @Description 请求日志信息
 * @Author qp
 * @Date 2019/2/27 14:07
 **/
public class ControllerInterceptor implements HandlerInterceptor {

    private static final transient Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);

    /**
     * 业务处理之前执行打印当前参数的日志
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return booLean 是否需要往下执行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String parameter = request.getParameter(name);
            log.info(request.getRequestURI() +"参数："+ String.format("%s --> %s",name,parameter));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
