package com.example.demo.cyclopedia.mapper;

import com.example.demo.cyclopedia.pojo.Cyclopedias;
import com.example.demo.cyclopedia.pojo.CyclopediasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclopediasMapper {
    long countByExample(CyclopediasExample example);

    int deleteByExample(CyclopediasExample example);

    int deleteByPrimaryKey(String id);

    int insert(Cyclopedias record);

    int insertSelective(Cyclopedias record);

    List<Cyclopedias> selectByExample(CyclopediasExample example);

    Cyclopedias selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Cyclopedias record, @Param("example") CyclopediasExample example);

    int updateByExample(@Param("record") Cyclopedias record, @Param("example") CyclopediasExample example);

    int updateByPrimaryKeySelective(Cyclopedias record);

    int updateByPrimaryKey(Cyclopedias record);

    //通过关键字查询百科内容
    List<Cyclopedias> selectCyclopediasByKeyWord(@Param("keyWord") String keyWord);
}