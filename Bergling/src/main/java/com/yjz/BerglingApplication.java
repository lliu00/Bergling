package com.yjz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BerglingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BerglingApplication.class, args);
    }

}
