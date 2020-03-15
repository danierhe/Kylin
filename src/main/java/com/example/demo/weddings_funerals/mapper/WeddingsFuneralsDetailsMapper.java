package com.example.demo.weddings_funerals.mapper;

import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsDetails;
import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface WeddingsFuneralsDetailsMapper {
    long countByExample(WeddingsFuneralsDetailsExample example);

    int deleteByExample(WeddingsFuneralsDetailsExample example);

    int deleteByPrimaryKey(String id);

    int insert(WeddingsFuneralsDetails record);

    int insertSelective(WeddingsFuneralsDetails record);

    List<WeddingsFuneralsDetails> selectByExample(WeddingsFuneralsDetailsExample example);

    WeddingsFuneralsDetails selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WeddingsFuneralsDetails record, @Param("example") WeddingsFuneralsDetailsExample example);

    int updateByExample(@Param("record") WeddingsFuneralsDetails record, @Param("example") WeddingsFuneralsDetailsExample example);

    int updateByPrimaryKeySelective(WeddingsFuneralsDetails record);

    int updateByPrimaryKey(WeddingsFuneralsDetails record);

    //删除- 通过主键id删除子表信息
    @Update("update weddings_funerals_details set status=0 where status=1 and parentId=#{parentId}")
    void updateByParentId(@Param("parentId") String parentId);

    //查询总金额
    @Select("select sum(gift_money) as giftMoney from weddings_funerals_details where status=1 and parent_id=#{parentId}")
    WeddingsFuneralsDetails getTotalMoneyByParentId(@Param("parentId") String parentId);
}