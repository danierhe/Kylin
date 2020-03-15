package com.example.demo.games.mapper;

import com.example.demo.games.pojo.GamesRecord;
import com.example.demo.games.pojo.GamesRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRecordMapper {
    long countByExample(GamesRecordExample example);

    int deleteByExample(GamesRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(GamesRecord record);

    int insertSelective(GamesRecord record);

    List<GamesRecord> selectByExample(GamesRecordExample example);

    GamesRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GamesRecord record, @Param("example") GamesRecordExample example);

    int updateByExample(@Param("record") GamesRecord record, @Param("example") GamesRecordExample example);

    int updateByPrimaryKeySelective(GamesRecord record);

    int updateByPrimaryKey(GamesRecord record);
}