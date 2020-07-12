package com.yjz.controller;

import com.yjz.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
