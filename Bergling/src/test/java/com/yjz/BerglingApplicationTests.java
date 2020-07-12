package com.yjz;

import com.yjz.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BerglingApplicationTests {

    @Autowired
    BlogMapper blogMapper;

    @Test
    void test() {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info(blogMapper.getById(1L).getContent());
    }

}
