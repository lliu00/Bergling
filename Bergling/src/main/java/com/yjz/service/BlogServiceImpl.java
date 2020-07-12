package com.yjz.service;

import com.yjz.mapper.BlogMapper;
import com.yjz.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> list() {
        return blogMapper.list();
    }

    @Override
    public Blog getById(Long id) {
        return blogMapper.getById(id);
    }
}
