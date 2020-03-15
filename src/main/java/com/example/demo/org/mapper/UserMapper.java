package com.example.demo.org.mapper;

import com.example.demo.org.pojo.User;
import com.example.demo.org.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select * from user")
    List<User> getUsersList();

    @Select("select * from user where password=#{password} and (phone =#{userName} or email = #{userName})")
    User selectUserByUserName(@Param("userName") String userName,String password);

    @Select("select * from user where status=1 and email=#{email}")
    User getUserByEmail(@Param("email") String email);

    @Select("select * from user where status=1 and phone=#{phone}")
    User getUserByPhone(@Param("phone") String phone);
}