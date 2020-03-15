package com.example.demo.conf;

import com.example.demo.base.conf.LoginUser;
import com.example.demo.org.pojo.User;
import com.example.demo.org.service.UserService;
import commons.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-28-02-28 21:26
 */
public class MyShiroRealm extends AuthenticatingRealm {

    @Autowired
    private UserService userService;

    //设置reaml的名称
    @Override
    public void setName(String name){
        super.setName("myShiroRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取用户输入的账号，密码
        String userName = (String) authenticationToken.getPrincipal();//账号
        String password = new String((char[]) authenticationToken.getCredentials());//密码
        //2查询用户信息
        User user = userService.getUserByUserNameAndPwd(userName, MD5.encryption(password));
        if(user==null){
            throw new UnknownAccountException("登录信息错误，请核实后重新尝试！");
        }
        //保存session
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(user.getUserName());
        loginUser.setUserId(user.getId());
        loginUser.setRoleId(user.getRoleId());
        loginUser.setCompanyId(user.getCompanyId());
        loginUser.setCompanyName(user.getCompanyName());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setAuthTree(user.getAuthTree());
        loginUser.setUserType(user.getUsetType());
        //3身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginUser,user.getPassword(),getName());
        //4用户账号验证
        if("0".equals(user.getStatus())){
            throw new DisabledAccountException("用户状态错误，请联系管理员！");
        }
        return simpleAuthenticationInfo;
    }

  /*  //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //创建授权信息对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        LoginUser loginUser = (LoginUser) subject.getPrincipal();
        if(loginUser!=null){
            //查询当前用户所有的权限信息
            *//*List<PlatSysMethod> sysMethodList = userService.getPermissinos(loginUser.getRoleId());
            if(sysMethodList.size()>0){
                Set<String> permissonSet = new HashSet<String>();
                for (PlatSysMethod sysmethod:sysMethodList) {
                    if(StringUtils.isNotBlank(sysmethod.getUrl())){
                        permissonSet.add(sysmethod.getUrl());
                    }
                }
                simpleAuthorizationInfo.setStringPermissions(permissonSet);
            }*//*
        }
        return simpleAuthorizationInfo;
    }*/




}
