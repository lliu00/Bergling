package com.yjz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjz.model.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/blogs").permitAll()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/hello").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ObjectMapper().writeValueAsString(Result.success().add("UserInfo", authentication.getPrincipal())));
                    printWriter.flush();
                    printWriter.close();
                })
                .failureHandler(((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ObjectMapper().writeValueAsString(Result.fail().add("ErrorMsg", e.getMessage())));
                    printWriter.flush();
                    printWriter.close();
                }))
                .and()
                .logout()
                .logoutUrl("/logout")
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
        auth.inMemoryAuthentication()
                .withUser("zhangxi").password("123456").roles("user")
                .and()
                .withUser("root").password("123456").roles("admin");
    }
}
