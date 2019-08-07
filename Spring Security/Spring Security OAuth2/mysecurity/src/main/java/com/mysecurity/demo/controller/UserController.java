package com.mysecurity.demo.controller;

import com.mysecurity.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("userList",userService.findAll());
        return "index";
    }
}
