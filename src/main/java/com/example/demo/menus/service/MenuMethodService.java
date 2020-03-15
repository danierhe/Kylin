package com.example.demo.menus.service;

import com.example.demo.base.BaseService;
import com.example.demo.menus.mapper.MenuMethodMapper;
import com.example.demo.menus.pojo.MenuMethod;
import commons.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-01-02-01 15:39
 */
@Service
@Transactional
public class MenuMethodService extends BaseService {

    @Autowired
    private MenuMethodMapper methodMapper;

    /**
    * @Author: DanierHe
    * @Date: 2020-02-01 下午 03:41
    * @Description: 新增菜单
    * @Param: [method]
    * @return: int
    */
    public int saveMenus(MenuMethod method) throws Exception{
        method.setId(getKey("method"));
        method.setCreateTime(DateUtils.getYmdhms());
        method.setStatus(USABLE_STATUS);
        return methodMapper.insert(method);
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-01 下午 03:42
    * @Description: 根据主键查询菜单详情
    * @Param: [id]
    * @return: com.example.demo.menus.pojo.MenuMethod
    */
    public MenuMethod selectMethodById(String id) throws Exception{
        return methodMapper.selectByPrimaryKey(id);
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-01 下午 03:46
    * @Description: 根据父id，查询子菜单
    * @Param: [parentId]
    * @return: java.util.List<com.example.demo.menus.pojo.MenuMethod>
    */
    public List<MenuMethod> getMenuMethodListByParentId(String parentId) throws Exception{
        List<MenuMethod> list = methodMapper.getMenuMethodListByParentId(parentId);
        return list;
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-01 下午 04:27
    * @Description: 查询全部菜单
    * @Param: []
    * @return: java.util.List<com.example.demo.menus.pojo.MenuMethod>
    */
    public List<MenuMethod> getAllMenuMethod() throws Exception{
        List<MenuMethod> list = methodMapper.getAllMenuMethod();
        return list;
    }


    //组装菜单
    public void saveUserRoles(String menuStr) throws Exception{
        //分割
        String[] strs = menuStr.split(",");
        if(strs.length>0){
            for(int i=0;i<strs.length;i++){
                MenuMethod menuMethod = methodMapper.selectByPrimaryKey(strs[i]);
                if(StringUtils.isNotBlank(menuMethod.getMenuLevel())){
                    if("1".equals(menuMethod.getMenuLevel())){
                        //一级菜单

                    }
                }
            }
        }
    }










}
