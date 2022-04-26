package com.blog.service;

import org.springframework.stereotype.Service;

public interface BlogTagService {
    void saveBlogTag(Long id, String[] ids);
    void updateBlogTag(Long id, String[] ids);

    void deleteById(Long id);

}
