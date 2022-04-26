package com.blog.service.impl;

import com.blog.mapper.BlogTagMapper;
import com.blog.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public void saveBlogTag(Long id, String[] ids) {
        blogTagMapper.saveBlogTag(id, ids);
    }

    @Override
    @Transactional
    public void updateBlogTag(Long id, String[] ids) {
        blogTagMapper.deleteById(id);
        blogTagMapper.saveBlogTag(id, ids);
    }

    @Override
    public void deleteById(Long id) {
        blogTagMapper.deleteById(id);
    }
}
