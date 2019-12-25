package com.example.demo.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-17-12-17 18:21
 */
@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/index")
    public String index2(){
        return "index";
    }

    //用户注册
    @RequestMapping("/users/user-register")
    public String userRegister(){
        return "users/user-register";
    }

    @RequestMapping("/work")
    public String work(){
        return "work";
    }


    //轮播图设置页面
    @RequestMapping("/carouselManager")
    public String carouselManager(){
        return "admin/carousel/carousel-manager";
    }
}
