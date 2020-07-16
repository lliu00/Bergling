package com.yjz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjz.model.Blog;
import com.yjz.model.Result;
import com.yjz.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping({"/", "/blogs"})
    public Result list(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageHelper.startPage(page, 4);
        List<Blog> blogs = blogService.list();
        PageInfo pageInfo = new PageInfo(blogs);
        return Result.success().add("pageInfo", pageInfo);
    }

    @GetMapping("/blogs/{id}")
    @PermitAll
    public Result detail(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info(blog.toString());
        return Result.success().add("blog", blog);
    }


    //新增博客和编辑博客怎么区分
    @PostMapping("/blogs")
    public Result edit(@Validated  @RequestBody Blog blog) {

        Blog bean = null;
        //文章ID存在，编辑文章
        if (blog.getId() != null) {
            bean = blogService.getById(blog.getId());
            BeanUtils.copyProperties(blog, bean, "id", "createdBy", "createdAt");
            blogService.update(bean);
        } else {
            bean = new Blog();
            bean.setCreatedAt(LocalDateTime.now());
            BeanUtils.copyProperties(blog, bean, "id", "createdBy", "createdAt");
            blogService.save(bean);
        }
        return Result.success("保存成功").add("blog", blog);
    }
}
