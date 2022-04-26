package com.blog.service;

import com.blog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface ArchiveService {
    Map<String, List<Blog>> archiveBlog();
    Long countBlog();
}
