package com.yjz;

import com.yjz.mapper.UserMapper;
import com.yjz.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BerglingApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void test() {
        Logger logger = LoggerFactory.getLogger(getClass());
        User user = userMapper.getByUsername("admin");
        logger.info(user.toString());
        Assertions.assertEquals("admin", user.getRole());
    }

}
