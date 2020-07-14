package com.yjz.service;

import com.yjz.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> list();

    Blog getById(Long id);

    Blog update(Blog blog);

    Blog save(Blog blog);
}
