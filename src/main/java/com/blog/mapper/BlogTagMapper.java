package com.blog.mapper;

import com.blog.pojo.Blog;
import com.blog.pojo.vo.BlogInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogTagMapper {
    /**
     * 插入blog的id和tas id
     */
  void saveBlogTag(@Param("id") Long id, @Param("ids") String[] ids);

    /*根据blog_id删除*/
  void deleteById(Long id);
}
