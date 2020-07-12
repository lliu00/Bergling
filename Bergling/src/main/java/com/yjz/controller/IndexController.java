package com.yjz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello Bergling!";
    }
}
