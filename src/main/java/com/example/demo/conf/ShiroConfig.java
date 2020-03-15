package com.example.demo.conf;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-03-15-03-15 10:14
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置拦截器
     * 定义拦截url权限，优先级从上到下，
     * 1）：anon：匿名访问，无需登录
     * 2）：authc：登录后才能访问
     * 3）：logout：登出
     * 4）：roles：角色过滤器
     *
     *  URL 匹配风格
     *  1） ？ 匹配一个字符，如/admin? 将匹配/admin1,但不匹配/admin 或 /admin/;
     *  2)  *  匹配0个或多个字符串，如/admin* 将匹配/admin1 或 /amdin12345,但不匹配/admin/1;
     *  3)  ** 匹配路径中的0个或多个路径， 如/admin/**  将匹配 /admin/a 或 /admin/a/b
     *
     * 配置身份验证成功，时报的跳转路径
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //将自定义的权限认证失败的过滤器shirofilter
        filters.put("perms" , new ShiroPermissinosFilter());
        filters.put("authc" , new LoginFilter());
        shiroFilterFactoryBean.setFilters(filters);//拦截请求。


        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainMap = new LinkedHashMap<String, String>();
        filterChainMap.put("/static/**","anon");//静态资源匿名访问
        filterChainMap.put("/favicon.ico","anon");//静态资源匿名访问
        filterChainMap.put("/index","anon");//登录匿名访问-跳转首页
        filterChainMap.put("/login","anon");//登录匿名访问-跳转登录页面

        filterChainMap.put("/indexAnnouncementPage","anon");//登录匿名访问-跳转首页-公告列表页
        filterChainMap.put("/indexAnnouncementDetail","anon");//登录匿名访问-跳转首页-公告详情页
        filterChainMap.put("/indexRulesRegulations","anon");//登录匿名访问-跳转首页-规章制度列表页
        filterChainMap.put("/helpCentre","anon");//登录匿名访问-跳转首页-帮助中心页
        filterChainMap.put("/login/getUserInfo","anon");//登录匿名访问-跳转首页-帮助中心页

        filterChainMap.put("/login/userLoginTwo","anon");//登录匿名访问-访问用户登录接口
        filterChainMap.put("/login/logout","anon");//用户退出，只需配置logout即可实现该功能
        filterChainMap.put("/user/indexUser","anon");//首页，判断用户是否登录
        filterChainMap.put("/sysInfo/getInfoPage","anon");//首页数据加载，查询公告信息
        filterChainMap.put("/sysInfo/getInfoById","anon");//首页数据加载，查看公告信息


        filterChainMap.put("/**","authc");//其他路径均需要身份认证，位于最下面，优先级最低

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        shiroFilterFactoryBean.setUnauthorizedUrl("/index");

        shiroFilterFactoryBean.setLoginUrl("/index");//首页
        return shiroFilterFactoryBean;
    }


    @Bean(name = "sessionDAO")
    public MemorySessionDAO getMemorySessionDAO(){
        return new MemorySessionDAO();
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSimpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("SHIROSESSIONID");
        return simpleCookie;
    }
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(getMemorySessionDAO());
        defaultWebSessionManager.setSessionIdCookie(getSimpleCookie());

        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }

    @Bean(name = "shiroCacheManager")
    public MemoryConstrainedCacheManager getMemoryConstrainedCacheManager(){
        return new MemoryConstrainedCacheManager();
    }


    /***
     *   SecurityManager ：安全管理器：shiro的核心
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /****
     *  自定义Realm，可以多个
     * @return
     */
    @Bean
    public MyShiroRealm shiroRealm(){
        MyShiroRealm shiroRealm = new MyShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }


    /***
     *  配置Shiro 的生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /***
     *  自动创建代理类，若不添加，Shiro的注解可能不会生效。
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }
    /***
     *  开启Shiro的注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager());
        return attributeSourceAdvisor;
    }

    /***
     *  凭证匹配器
     *  密码校验交给Shiro的SimpleAuthenticationInfo进行处理
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        return hashedCredentialsMatcher;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
