package com.yjz.controller;

import com.yjz.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
}
