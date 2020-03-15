package com.example.demo.menus.mapper;

import com.example.demo.menus.pojo.MenuMethod;
import com.example.demo.menus.pojo.MenuMethodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMethodMapper {
    long countByExample(MenuMethodExample example);

    int deleteByExample(MenuMethodExample example);

    int deleteByPrimaryKey(String id);

    int insert(MenuMethod record);

    int insertSelective(MenuMethod record);

    List<MenuMethod> selectByExample(MenuMethodExample example);

    MenuMethod selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MenuMethod record, @Param("example") MenuMethodExample example);

    int updateByExample(@Param("record") MenuMethod record, @Param("example") MenuMethodExample example);

    int updateByPrimaryKeySelective(MenuMethod record);

    int updateByPrimaryKey(MenuMethod record);

    // 根据上级菜单查询子菜单
    @Select("select * from menu_method where status=1 and parent_id = #{parentId}")
    List<MenuMethod> getMenuMethodListByParentId(@Param("parentId") String parentId);

    //查询全部菜单
    @Select("select * from menu_method where status=1 ")
    List<MenuMethod> getAllMenuMethod();
}