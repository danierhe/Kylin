package com.example.demo.menus.controller;

import com.example.demo.base.BaseController;
import com.example.demo.menus.service.MenuMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-01-02-01 16:27
 */
@Controller
@RequestMapping("/menus")
public class MenuMehotdController extends BaseController {

    @Autowired
    private MenuMethodService methodService;

}
