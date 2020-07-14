package com.yjz.mapper;

import com.yjz.model.Blog;
import com.yjz.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getById(Long id);

    User getByUsername(String username);

}
