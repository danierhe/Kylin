package com.example.demo.cyclopedia.mapper;

import com.example.demo.cyclopedia.pojo.CyclopediasCatalogues;
import com.example.demo.cyclopedia.pojo.CyclopediasCataloguesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclopediasCataloguesMapper {
    long countByExample(CyclopediasCataloguesExample example);

    int deleteByExample(CyclopediasCataloguesExample example);

    int deleteByPrimaryKey(String id);

    int insert(CyclopediasCatalogues record);

    int insertSelective(CyclopediasCatalogues record);

    List<CyclopediasCatalogues> selectByExample(CyclopediasCataloguesExample example);

    CyclopediasCatalogues selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CyclopediasCatalogues record, @Param("example") CyclopediasCataloguesExample example);

    int updateByExample(@Param("record") CyclopediasCatalogues record, @Param("example") CyclopediasCataloguesExample example);

    int updateByPrimaryKeySelective(CyclopediasCatalogues record);

    int updateByPrimaryKey(CyclopediasCatalogues record);

    @Select("select * from cyclopedias_catalogues where status=1 and cyclopedia_id=#{cyclopediaId} order by create_time asc")
    List<CyclopediasCatalogues> getCyclopediasCataloguesByCyclopediasId(@Param("cyclopediaId") String cyclopediaId);

    @Update("update cyclopedias_catalogues set cyclopedia_id=#{newCyclopediasId}  where status=1 and cyclopedia_id=#{oldCyclopediasId}")
    void updateCyclopediasId(@Param("newCyclopediasId") String newCyclopediasId, @Param("oldCyclopediasId") String oldCyclopediasId);
}