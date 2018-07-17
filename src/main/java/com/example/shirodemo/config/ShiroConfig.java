package com.example.shirodemo.config;

import com.example.shirodemo.config.auth.Realm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
   @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){

       //以下相当于shiro配置的xml文件的java写法

       //注入ShiroFilterFactoryBean
       ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
       shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
       Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

       //设置登录url
       shiroFilterFactoryBean.setLoginUrl("/login.html");
       //登录成功后跳转的url
       shiroFilterFactoryBean.setSuccessUrl("/index.html");
       //无权限的跳转页面
       shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");




       /**
        *相当于xml设置某些权限可以访问的页面,可以将有权限要求的页面进行保护
        * anno代表任何人可以访问 authc需要登录才能访问
        */
       filterChainDefinitionMap.put("/logout.html", "logout");
       filterChainDefinitionMap.put("/login.html", "anon");
       filterChainDefinitionMap.put("/admin/*", "authc, roles[admin]");
       filterChainDefinitionMap.put("/test.html","authc");

       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

       return shiroFilterFactoryBean;
   }

    /**
     * 配置核心安全事务管理器
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(Realm realm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    @Bean
    public Realm myRealm(){
        Realm realm = new Realm();//这里实例化一个我们自己写的Realm类
       // realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return  realm;
    }

//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//
//        return hashedCredentialsMatcher;
//    }

//    /**
//     * 生命周期处理器
//     * @return
//     */
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
//    {
//        return new LifecycleBeanPostProcessor();
//    }
//
//
//    /**
//     * 开启shiro注解
//     * @return
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
//    {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
    /**
     *  开启shiro aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    }



