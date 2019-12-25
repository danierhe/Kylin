package com.example.demo.login.controller;

import com.example.demo.base.BaseController;
import com.example.demo.org.pojo.Users;
import com.example.demo.org.service.UserService;
import commons.MD5;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-23-12-23 13:59
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/userLogin")
    public Map userLogin(String userName, String password){
        try {
            if(StringUtils.isBlank(userName)){
                return error("账号不能为空！");
            }
            if(StringUtils.isBlank(password)){
                return error("密码不能为空！");
            }
            Users users = userService.getUserByUserNameAndPwd(userName,MD5.encryption(password));
            if(users==null){
               return error("账号或密码错误！");
            }else if(users.getUserStatus().equals(0)){
               return error("当前用户已被停用！");
            }else{
                return success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("LoginController - userLogin - error");
            return error("系统异常！");
        }
    }
}
