package com.example.demo.conf;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-28-02-28 21:14
 */
public class LoginFilter extends FormAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)throws Exception{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //判断是否为ajax请求
        boolean isAjax = false;
        if(!StringUtils.isBlank(req.getHeader("Accept")) && req.getHeader("Accept").equals("application/json, text/javascript, */*; q=0.01")){
            isAjax = true;
        }

        if(isAjax){
            logger.info("===============  ajax请求  ============");
            Map<String,String> map = new HashMap<>();
            map.put("code","400");
            map.put("msg","请登录后尝试！");

            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            resp.getWriter().write(JSONObject.toJSONString(map));
            resp.setHeader("REDIRECT","REDIRECT");
            resp.setHeader("CONTEXTPATH","/index");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else {
            logger.info("===============  非ajax请求  ============");
            saveRequestAndRedirectToLogin(request,response);
        }
        return false;
    }

}
