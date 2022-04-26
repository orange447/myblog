package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.pojo.Type;
import com.blog.pojo.vo.BlogInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);

    Blog getById(Long id);

    PageInfo<Blog> splitPage(int currentPageNum, int pageSize, BlogInfoVo blogVo);

    Long saveBlog(Blog blog);

    PageInfo<Blog> blogTypeId(int currentPageNum, int pageSize, Long id);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    PageInfo<Blog> splitPage(int currentPageNum, int pageSize);

    PageInfo<Blog> split(int currentPageNum, int pageSize);

    List<Blog> listBlogTop(Integer size);

    PageInfo<Blog> blogQuery(String query, int currentPageNum, int pageSize);

    Blog getAndConvert(Long id);

    PageInfo<Blog> blogTagId(int currentPageNum, int pageSize, Long id);
}
