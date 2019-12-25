package com.example.demo.base;

import commons.PageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-17-12-17 16:21
 */
public class BaseController {
    public static final String USABLE_STATUS="1";//可以
    public static final String DELETE_STATUS="0";//删除，不可用

    protected Map error(String msg){
        Map<String,String> map = new HashMap<>();
        map.put("code","400");
        map.put("msg",msg);
        return map;
    }

    protected Map success(){
        Map<String,String> map = new HashMap<>();
        map.put("code","200");
        map.put("msg","成功");
        return map;
    }

    protected Map showSucc(Object obj){
        Map<String,Object> map = new HashMap<>();
        map.put("code","200");
        map.put("msg","成功");
        map.put("data",obj);
        return map;
    }

    protected Map showSuccMsg(String code,String msg,Object obj){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",obj);
        return map;
    }

    protected <T> Map showLayTale(PageBean<T> pageBean){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","成功");
        map.put("count",pageBean.getCount());
        map.put("data",pageBean.getData());
        return map;
    }

}
