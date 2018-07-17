package com.example.shirodemo.controller;

import com.example.shirodemo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("test")
    public User test(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession());
        User user = (User) subject.getPrincipal();
        System.out.println("+++++++++sessionId  " +subject.getSession().getId());
        System.out.println("+++++++++name  " +subject.getSession().getAttribute("name"));
        return user;
    }

}
