package com.yjz.config;

import com.yjz.model.User;
import com.yjz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logger logger = LoggerFactory.getLogger(getClass());
        User user = userService.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        logger.warn("======================");
        logger.warn("User: " + username);
        logger.warn("UserId: " + user.getId());
        logger.warn("UserPassword: " + user.getPassword());
        logger.warn("UserRole: " + user.getRole());
        logger.warn("======================");

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
    }

}
