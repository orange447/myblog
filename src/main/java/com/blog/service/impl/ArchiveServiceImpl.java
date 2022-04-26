package com.blog.service.impl;

import com.blog.mapper.ArchiveMapper;
import com.blog.pojo.Blog;
import com.blog.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = archiveMapper.findGroupYears();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, archiveMapper.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return archiveMapper.count();
    }
}
