package com.example.demo.daily_cost.mapper;

import com.example.demo.daily_cost.pojo.DailyCost;
import com.example.demo.daily_cost.pojo.DailyCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCostMapper {
    long countByExample(DailyCostExample example);

    int deleteByExample(DailyCostExample example);

    int deleteByPrimaryKey(String id);

    int insert(DailyCost record);

    int insertSelective(DailyCost record);

    List<DailyCost> selectByExample(DailyCostExample example);

    DailyCost selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DailyCost record, @Param("example") DailyCostExample example);

    int updateByExample(@Param("record") DailyCost record, @Param("example") DailyCostExample example);

    int updateByPrimaryKeySelective(DailyCost record);

    int updateByPrimaryKey(DailyCost record);

    //查询列表
    List<DailyCost> getDailyCostList(@Param("name") String name, @Param("startTime") String startTime, @Param("endTime") String endTime);
}