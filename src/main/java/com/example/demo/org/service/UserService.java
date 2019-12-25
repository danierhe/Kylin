package com.example.demo.org.service;

import com.example.demo.base.BaseService;
import com.example.demo.org.mapper.UsersMapper;
import com.example.demo.org.pojo.Users;
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
    private UsersMapper usersMapper;

    /**
    * @Author: DanierHe
    * @Date: 2019-12-17 下午 04:48
    * @Description:
    * @Param: [userId]
    * @return: com.example.demo.users.pojo.Users
    */
    public Users getUserById(String userId){
        logger.info("========service=======");
        Users user = usersMapper.selectByPrimaryKey(userId);
        return user;
    }

    public Users getUserByPhone(String phone) throws Exception{
        return usersMapper.getUserByPhone(phone);
    }

    public Users getUserByEmail(String email) throws Exception{
        return usersMapper.getUserByEmail(email);
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-17 下午 05:20
    * @Description: 新增用户
    * @Param: []
    * @return: java.lang.Integer
    */
    public Integer insertUser(){
        Users users = new Users();
        users.setId(System.currentTimeMillis()+"");
        users.setUserName("张三");
        return usersMapper.insert(users);
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-23 下午 02:06
    * @Description: 用户登录
    * @Param: [userName, password]
    * @return: com.example.demo.users.pojo.Users
    */
    public Users getUserByUserNameAndPwd(String userName,String password) throws Exception{
        return usersMapper.selectUserByUserName(userName,password);
    }








    public PageBean<Users> getUserPage(PageBean<Users> pageBean,String searchParam){
        //开启分页
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<Users> usersList = usersMapper.getUsersList();
        PageInfo<Users> pageInfo = new PageInfo<>(usersList);
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
    public int saveUser(Users users) throws Exception{
        users.setId(getKey("user"));
        users.setStatus(USABLE_STATUS);
        users.setUserStatus("1");
        users.setCreateTime(DateUtils.getYmdhms());
        return usersMapper.insert(users);
    }
}
