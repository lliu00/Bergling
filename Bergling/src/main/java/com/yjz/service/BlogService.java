package com.yjz.service;

import com.yjz.model.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogService {
    List<Blog> list();

    Blog getById(Long id);
}
