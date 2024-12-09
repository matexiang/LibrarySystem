package com.lxy.book.controller;

import com.lxy.book.constant.Constants;
import com.lxy.book.model.UserInfo;
import com.lxy.book.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequestMapping("/user")
@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Boolean login(String userName, String password, HttpSession session){
        log.info("用户登录 userName:{}, password: {}", userName, password);

        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
            System.out.println(false);
            return false;
        }
        UserInfo userInfo = userService.queryUserByName(userName);

        if(userInfo==null || userInfo.getId()<0){
            System.out.println("用户不存在");
            return false;
        }
        if(!password.equals(userInfo.getPassword())){
            System.out.println("账号或密码错误");
            return false;
        }
        //存储session
        userInfo.setPassword("");
        session.setAttribute(Constants.USER_SESSION_KEY,userInfo);
        System.out.println("登录正常");
        return true;

/*
        if ("admin".equals(userName) && "admin".equals(password)){
            //账号密码是正确的
            //session的内容, 取决于后面需要从session中获取什么
            session.setAttribute("userName", userName);
            System.out.println(true);
            return true;
        }
        System.out.println(false);
        return false;*/
    }
}
