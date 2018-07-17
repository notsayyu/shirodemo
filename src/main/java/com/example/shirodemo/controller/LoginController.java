package com.example.shirodemo.controller;


import com.example.shirodemo.model.User;
import com.example.shirodemo.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sign")
public class LoginController {

    @PostMapping("login")
    public String login(@RequestBody User user){
        System.out.println("++++++++++++==");
        String name =user.getName();
        String password =  MD5Util.toMD5(user.getPassword());
        System.out.println(name);
        //System.out.println("****" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Session session = subject.getSession(true);
        session.setAttribute("name",name);
        System.out.println("+++++++++sessionId  " +session.getId());
        System.out.println("+++++++++time  " +session.getTimeout());
        System.out.println("+++++++++name  " +session.getAttribute("name"));
        return "loginSuccess";
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("+++++++++sessionId  " + subject.getSession().getId());
        System.out.println("+++++++++time  " + subject.getSession().getTimeout());
        System.out.println("+++++++++name  " +subject.getSession().getAttribute("name"));
        subject.logout();
        return "success";
    }

    @RequestMapping("index")
    public User Home(){
        Subject subject = SecurityUtils.getSubject();
        User principal = (User)subject.getPrincipal();
        return principal;
    }

}
