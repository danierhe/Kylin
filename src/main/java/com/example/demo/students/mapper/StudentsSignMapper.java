package com.example.demo.students.mapper;

import com.example.demo.students.pojo.StudentsSign;
import com.example.demo.students.pojo.StudentsSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsSignMapper {
    long countByExample(StudentsSignExample example);

    int deleteByExample(StudentsSignExample example);

    int deleteByPrimaryKey(String id);

    int insert(StudentsSign record);

    int insertSelective(StudentsSign record);

    List<StudentsSign> selectByExample(StudentsSignExample example);

    StudentsSign selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StudentsSign record, @Param("example") StudentsSignExample example);

    int updateByExample(@Param("record") StudentsSign record, @Param("example") StudentsSignExample example);

    int updateByPrimaryKeySelective(StudentsSign record);

    int updateByPrimaryKey(StudentsSign record);

    //查询当前是否已经签到
    @Select("select  students_sign where status=1 and student_id=#{studentId} and sign_time  LIKE CONCAT('%',#{signTime},'%')   ")
    StudentsSign SignOrNot(@Param("studentId")String studentId, @Param("signTime") String signTime);
}