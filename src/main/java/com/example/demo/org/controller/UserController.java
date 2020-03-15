package com.example.demo.org.controller;

import com.example.demo.base.BaseController;
import com.example.demo.org.pojo.User;
import com.example.demo.org.service.UserService;
import commons.MD5;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-17-12-17 16:07
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/saveUser")
    public Map saveUser(User users){
        try {
            //判断用户注册手机号和邮件是否存在
            User phones = userService.getUserByPhone(users.getPhone());
            if(phones!=null){
                return error("手机号已存在！");
            }
            User emails = userService.getUserByEmail(users.getEmail());
            if(emails!=null){
                return error("邮箱已存在！");
            }
            //MD5加密 密码
            String password = MD5.encryption(users.getPassword());
            users.setPassword(password);
            int i = userService.saveUser(users);
            if(i==1){
                return success();
            }else {
                return error("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("UserController - saveUser - error");
            return error("系统错误，请联系管理员！");
        }
    }






    @RequestMapping("/getUserById")
    public Map getUserById(String userId){
        User user = userService.getUserById(userId);
        return showSucc(user);
    }


    @RequestMapping("/addUser")
    public Map addUser(){
        userService.insertUser();
        return success();
    }

    @RequestMapping("/getUserPage")
    public Map getUserPage(PageBean<User> pageBean,String searchParam){
        pageBean = userService.getUserPage(pageBean,searchParam);
        return showLayTale(pageBean);
    }

}
