package com.example.demo.weddings_funerals.mapper;

import com.example.demo.weddings_funerals.pojo.WeddingsFunerals;
import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeddingsFuneralsMapper {
    long countByExample(WeddingsFuneralsExample example);

    int deleteByExample(WeddingsFuneralsExample example);

    int deleteByPrimaryKey(String id);

    int insert(WeddingsFunerals record);

    int insertSelective(WeddingsFunerals record);

    List<WeddingsFunerals> selectByExample(WeddingsFuneralsExample example);

    WeddingsFunerals selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WeddingsFunerals record, @Param("example") WeddingsFuneralsExample example);

    int updateByExample(@Param("record") WeddingsFunerals record, @Param("example") WeddingsFuneralsExample example);

    int updateByPrimaryKeySelective(WeddingsFunerals record);

    int updateByPrimaryKey(WeddingsFunerals record);
}