package com.yjz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjz.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import java.io.PrintWriter;
import java.util.Enumeration;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Logger logger = LoggerFactory.getLogger(getClass());
        http.cors().and().csrf().disable().authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .rememberMe().tokenValiditySeconds(1209600)
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/blogs", "/", "/blogs/{id}").permitAll()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/hello").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login")
                .permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    Enumeration<?> enumeration = httpServletRequest.getSession().getAttributeNames();
                    while (enumeration.hasMoreElements()) {
                        String name = enumeration.nextElement().toString();
                        Object value = httpServletRequest.getSession().getAttribute(name);
                        logger.warn(name + ":" + value);
                    }
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    Result result = Result.success().add("UserInfo", authentication.getPrincipal());
                    printWriter.write(new ObjectMapper().writeValueAsString(result));
                    printWriter.flush();
                    printWriter.close();
                })
                .failureHandler(((httpServletRequest, httpServletResponse, e) -> {
                    logger.warn(httpServletRequest.getParameter("username"));
                    logger.warn(httpServletRequest.getParameter("password"));
                    logger.warn(httpServletResponse.toString());
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ObjectMapper().writeValueAsString(Result.fail().add("ErrorMsg", e.getMessage())));
                    printWriter.flush();
                    printWriter.close();
                }))
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = resp.getWriter();
                    printWriter.write(new ObjectMapper().writeValueAsString(Result.success().add("UserInfo",authentication)));
                })
                .and()
                .exceptionHandling().
                authenticationEntryPoint((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = resp.getWriter();
                    printWriter.write(new ObjectMapper().writeValueAsString(Result.fail().add("ErrorMsg", "尚未登录，请登录!")));
                    printWriter.flush();
                    printWriter.close();
                });

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }
}
