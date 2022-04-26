package com.blog.service;


import com.blog.pojo.Tag;
import com.blog.pojo.vo.TagBlogVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TagService {
    Long saveTag(Tag tag);
    Tag getOneTag(Long id);
    Tag getByName(String name);
    PageInfo<Tag> splitPage(int currentPageNum, int pageSize);
    List<Tag> getAllTag();
    int updateTag(Long id, Tag type);
    int delete(Long id);
    List<TagBlogVo> listTagTop(Integer size);
    List<Tag> getByIds(String[] ids);
}
