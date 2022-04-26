package com.blog.mapper;

import com.blog.pojo.Blog;
import com.blog.pojo.Tag;
import com.blog.pojo.vo.TagBlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArchiveMapper {
    List<String> findGroupYears();

    List<Blog> findByYear(String year);

    Long count();
}
