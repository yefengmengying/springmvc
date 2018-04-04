package com.ssm.controller;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login (User user){
        ModelAndView mav = new ModelAndView();
        System.out.println(user.getUsername() + " " + user.getPassword());
        if(!userService.loginCheck(user)) {
            mav.setViewName("login.jsp");
            mav.addObject("errorMsg", "用户名或密码错误！");
        } else {
            mav.setViewName("success.jsp");
            mav.addObject("user", user);
        }
        return mav;
    }

    @RequestMapping(value = "/register")
    public ModelAndView register(User user){
        ModelAndView mav = new ModelAndView();
        System.out.println(user.getUsername() + " " + user.getPassword());
        if (!userService.register(user)){
            mav.setViewName("register.jsp");
            mav.addObject("errorMsg", "该用户已被注册！");
        }else {
            mav.setViewName("index.jsp");
            mav.addObject("user", user);
        }
        return mav;
    }

}
