package com.example.demo.conf;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-03-15-03-15 10:17
 */
public class ShiroPermissinosFilter extends PermissionsAuthorizationFilter {

    private static final Logger log = LoggerFactory.getLogger(ShiroPermissinosFilter.class);

    /**
     * @Author: DanierHe
     * @Date: 2019-09-06 下午 05:22
     * @Description:  shiro 认证perms资源失败后回调方法。
     * @Param: [req, resp]
     * @return: boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp) throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestType = request.getHeader("X-Request-With");
        log.info("==============================================");
        log.info("=======================接口权限验证=======================");
        log.info("==============================================");
        if("XMLHttpRequest".equals(requestType)){
            log.info("当前请求为ajax请求！");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            //返回禁止访问json字符串
            Map<String,String> map = new HashMap<>();
            map.put("status","400");
            map.put("msg","无权限");
            response.getWriter().write(JSONObject.toJSONString(map).toString());
        }else {
            //如果请求类型不是ajax，那么此时requestpye为null
            log.info("非ajax请求！");
            response.sendRedirect("/index");
        }
        return false;
    }
}
