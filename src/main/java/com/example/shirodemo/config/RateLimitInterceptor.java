package com.example.shirodemo.config;

import com.example.shirodemo.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("用户IP： " + httpServletRequest.getRemoteAddr());
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getRequestURL());
        String ip = IpUtil.getIPAddress(httpServletRequest);
        System.out.println("true Ip: " + ip);
        String path = httpServletRequest.getRequestURI();

        Jedis jedis = jedisPool.getResource();

        String  key = ip + path;

        String current = jedis.get(key);
        System.out.println("key = "+key+" ; value = :" + current);

        if(current != null && Integer.parseInt(current) >= 2) {
            System.out.println("即将抛出异常");
            throw new RateLimitException("访问当前api次数过于频繁，请稍事休息~");
        }

        if(current == null){
            jedis.incrBy(key, 1);
            System.out.println("current 为null， key的值+1.。key = "+key+" ; value = :" + current);
            jedis.expire(key, 60);
        }else {
            System.out.println("current 不为null， key的值+1.。key = "+key+" ; value = :" + current);
            jedis.incrBy(key, 1);
        }

        System.out.println("分隔符------------------------");
        System.out.println("key = "+key+" ; value = :" + current);


//        throw new RateLimitException("访问次数过多");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
