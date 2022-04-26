package com.blog.service.impl;

import com.blog.NotFoundException;
import com.blog.mapper.BlogMapper;
import com.blog.pojo.Blog;
import com.blog.pojo.vo.BlogInfoVo;
import com.blog.service.BlogService;
import com.blog.util.MarkdownUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public Blog getById(Long id) {

        return blogMapper.getById(id);
    }

    /**
     * 按条件分页
     *
     * @param currentPageNum
     * @param pageSize
     * @param blogVo
     * @return
     */
    @Override
    public PageInfo<Blog> splitPage(int currentPageNum, int pageSize, BlogInfoVo blogVo) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Blog> blogList = blogMapper.splitPage(blogVo);
        return new PageInfo<>(blogList);
    }

    /**
     * 根据分类id分页
     * @param currentPageNum
     * @param pageSize
     * @param id
     * @return
     */
    @Override
    public PageInfo<Blog> blogTypeId(int currentPageNum, int pageSize, Long id) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Blog> blogList = blogMapper.blogTypeId(id);
        return new PageInfo<>(blogList);
    }
    /**
     * 根据标签id分页
     * @param currentPageNum
     * @param pageSize
     * @param id
     * @return
     */
    @Override
    public PageInfo<Blog> blogTagId(int currentPageNum, int pageSize, Long id) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Blog> blogList = blogMapper.blogTagId(id);
        for (Blog blog : blogList) {
            System.out.println(blog.getTagList());
        }
        return new PageInfo<>(blogList);
    }
    /**
     * 新增
     *
     * @param blog
     * @return
     */
    @Override
    public Long saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.saveBlog(blog);
    }

    /**
     * 更新
     *
     * @param blog
     * @return
     */
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public PageInfo<Blog> splitPage(int currentPageNum, int pageSize) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Blog> allBlog = blogMapper.findAllDesc();
        return new PageInfo<>(allBlog);
    }

    @Override
    public PageInfo<Blog> split(int currentPageNum, int pageSize) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Blog> allBlog = blogMapper.findAll();
        return new PageInfo<>(allBlog);
    }

    /**
     * 推荐博客
     *
     * @param size
     * @return
     */
    @Override
    public List<Blog> listBlogTop(Integer size) {
        return blogMapper.listBlogTop(size);
    }

    /**
     * 根据搜索栏查询
     *
     * @param query
     * @return
     */
    @Override
    public PageInfo<Blog> blogQuery(String query, int currentPageNum, int pageSize) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Blog> allBlog = blogMapper.blogQuery(query);
        return new PageInfo<>(allBlog);
    }

    /**
     * Markdown转换html
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.getById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        String htmls = MarkdownUtil.markdownToHtmlExtensions(content);
        blog.setContent(htmls);
        int views = blogMapper.updateViews(id);

        return blog;
    }
}
