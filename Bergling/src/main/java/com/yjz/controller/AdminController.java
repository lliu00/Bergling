package com.yjz.controller;

import com.github.pagehelper.Page;
import com.yjz.model.Result;
import com.yjz.service.BlogService;
import com.yjz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;

}
