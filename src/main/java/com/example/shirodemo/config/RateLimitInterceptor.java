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
        String ip = IpUtil.getIp(httpServletRequest);
        System.out.println("true Ip: " + ip);
        String path = httpServletRequest.getRequestURI();

        String  key = ip + path;

        Jedis jedis = jedisPool.getResource();
        jedis.set(key, "dsygood");

        System.out.println("key = "+key+" ; value = :" + jedis.get(key));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
