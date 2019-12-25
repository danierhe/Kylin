package com.example.demo.org.mapper;


import com.example.demo.org.pojo.Users;
import com.example.demo.org.pojo.UsersExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    long countByExample(UsersExample example);

    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    @Select("select * from user")
    List<Users> getUsersList();

    @Select("select * from user where password=#{password} and (phone =#{userName} or email = #{userName})")
    Users selectUserByUserName(@Param("userName") String userName,String password);

    @Select("select * from user where status=1 and email=#{email}")
    Users getUserByEmail(@Param("email") String email);

    @Select("select * from user where status=1 and phone=#{phone}")
    Users getUserByPhone(@Param("phone") String phone);
}