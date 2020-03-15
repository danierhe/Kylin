package com.example.demo.org.service;

import com.example.demo.base.BaseService;
import com.example.demo.org.mapper.UserMapper;
import com.example.demo.org.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.DateUtils;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author DanierHe
 * @description
 * @date 2019-12-17-12-17 16:05
 */
@Service
@Transactional
public class UserService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    /**
    * @Author: DanierHe
    * @Date: 2019-12-17 下午 04:48
    * @Description:
    * @Param: [userId]
    * @return: com.example.demo.users.pojo.Users
    */
    public User getUserById(String userId){
        logger.info("========service=======");
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    public User getUserByPhone(String phone) throws Exception{
        return userMapper.getUserByPhone(phone);
    }

    public User getUserByEmail(String email) throws Exception{
        return userMapper.getUserByEmail(email);
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-17 下午 05:20
    * @Description: 新增用户
    * @Param: []
    * @return: java.lang.Integer
    */
    public Integer insertUser(){
        User users = new User();
        users.setId(System.currentTimeMillis()+"");
        users.setUserName("张三");
        return userMapper.insert(users);
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-23 下午 02:06
    * @Description: 用户登录
    * @Param: [userName, password]
    * @return: com.example.demo.users.pojo.Users
    */
    public User getUserByUserNameAndPwd(String userName,String password){
        return userMapper.selectUserByUserName(userName,password);
    }








    public PageBean<User> getUserPage(PageBean<User> pageBean, String searchParam){
        //开启分页
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<User> usersList = userMapper.getUsersList();
        PageInfo<User> pageInfo = new PageInfo<>(usersList);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(usersList);
        return pageBean;
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-23 下午 03:54
    * @Description: 保存用户信息
    * @Param: [users]
    * @return: int
    */
    public int saveUser(User users) throws Exception{
        users.setId(getKey("user"));
        users.setStatus(USABLE_STATUS);
        users.setUserStatus("1");
        users.setCreateTime(DateUtils.getYmdhms());
        return userMapper.insert(users);
    }
}
