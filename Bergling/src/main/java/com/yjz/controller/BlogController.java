package com.yjz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjz.model.Blog;
import com.yjz.model.Result;
import com.yjz.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping({"/", "/blogs"})
    public Result list(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageHelper.startPage(page, 5);
        List<Blog> blogs = blogService.list();
        PageInfo pageInfo = new PageInfo(blogs);
        return Result.success().add("pageInfo", pageInfo);
    }

    @GetMapping("/blogs/{id}")
    public Result detail(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info(blog.toString());
        return Result.success().add("blog", blog);
    }


    //新增博客和编辑博客怎么区分
    @PostMapping("/blogs")
    public Result edit(@RequestBody Blog blog) {
        //文章ID存在，编辑文章
        if (blog.getId() != null) {
            blogService.update(blog);
        } else {
            blogService.save(blog);
        }
        return Result.success("保存成功").add("blog", blog);
    }
}
