package com.example.demo.cyclopedia.mapper;

import com.example.demo.cyclopedia.pojo.CyclopediasCataloguesDetails;
import com.example.demo.cyclopedia.pojo.CyclopediasCataloguesDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclopediasCataloguesDetailsMapper {
    long countByExample(CyclopediasCataloguesDetailsExample example);

    int deleteByExample(CyclopediasCataloguesDetailsExample example);

    int deleteByPrimaryKey(String id);

    int insert(CyclopediasCataloguesDetails record);

    int insertSelective(CyclopediasCataloguesDetails record);

    List<CyclopediasCataloguesDetails> selectByExample(CyclopediasCataloguesDetailsExample example);

    CyclopediasCataloguesDetails selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CyclopediasCataloguesDetails record, @Param("example") CyclopediasCataloguesDetailsExample example);

    int updateByExample(@Param("record") CyclopediasCataloguesDetails record, @Param("example") CyclopediasCataloguesDetailsExample example);

    int updateByPrimaryKeySelective(CyclopediasCataloguesDetails record);

    int updateByPrimaryKey(CyclopediasCataloguesDetails record);

    //根据百科主表id，查询所有的内容
    @Select("select c1.*,c2.name as cataloguesName from cyclopedias_catalogues_details c1 left join cyclopedias_catalogues c2 on c1.catalogue_id = c2.id where c1.status=1 and c1.cyclopedias_id=#{cyclopediasId} order by c1.create_time asc")
    List<CyclopediasCataloguesDetails> getDetailsByCyclopediasId(@Param("cyclopediasId") String cyclopediasId);

    //通过目录表主键，查询当前目录下的内容
    @Select("select id,content from cyclopedias_catalogues_details where status=1 and catalogue_id = #{catalogueId} order by create_time asc")
    List<CyclopediasCataloguesDetails> getDetailsByCatalogueId(@Param("catalogueId") String catalogueId);

    @Update("update cyclopedias_catalogues_details set cyclopedias_id=#{newCyclopediasId}  where status=1 and cyclopedias_id=#{oldCyclopediasId}")
    void updateCyclopediasId(@Param("newCyclopediasId") String newCyclopediasId, @Param("oldCyclopediasId") String oldCyclopediasId);
}