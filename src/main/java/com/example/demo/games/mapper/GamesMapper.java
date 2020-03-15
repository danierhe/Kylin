package com.example.demo.games.mapper;

import com.example.demo.games.pojo.Games;
import com.example.demo.games.pojo.GamesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesMapper {
    long countByExample(GamesExample example);

    int deleteByExample(GamesExample example);

    int deleteByPrimaryKey(String id);

    int insert(Games record);

    int insertSelective(Games record);

    List<Games> selectByExample(GamesExample example);

    Games selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Games record, @Param("example") GamesExample example);

    int updateByExample(@Param("record") Games record, @Param("example") GamesExample example);

    int updateByPrimaryKeySelective(Games record);

    int updateByPrimaryKey(Games record);
}