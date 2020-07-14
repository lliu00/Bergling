package com.yjz.mapper;

import com.yjz.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    List<Blog> list();

    Blog getById(Long id);

    Blog update(@Param("blog") Blog blog);

    Blog save(@Param("blog") Blog blog);
}
