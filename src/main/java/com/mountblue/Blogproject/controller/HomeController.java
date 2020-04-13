package com.mountblue.Blogproject.controller;

import com.mountblue.Blogproject.entity.UserData;
import com.mountblue.Blogproject.repository.UserRepository;
import com.mountblue.Blogproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/homePage")
    public ModelAndView ViewHomePage(ModelAndView modelAndView, Authentication authentication){
        String name=authentication.getName();
        UserData  listUser=userService.findUser(name);
        modelAndView.addObject("listUser",listUser);
        modelAndView.setViewName("homePage");
        return modelAndView;
    }


}
