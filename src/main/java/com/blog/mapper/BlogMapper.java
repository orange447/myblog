package com.blog.mapper;

import com.blog.pojo.Blog;
import com.blog.pojo.vo.BlogInfoVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    Blog getById(Long id);

    /**
     * 分页条件查询
     *
     * @param blogVo
     * @return
     */
    List<Blog> splitPage(BlogInfoVo blogVo);

    /**
     * 新增
     *
     * @param blog
     * @return
     */
    Long saveBlog(Blog blog);

    /**
     * 更新
     *
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteBlog(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    /**
     * 查询部分降序排序
     *
     * @return
     */
    List<Blog> findAllDesc();

    /**
     * 根据指定数目的博客降序
     *
     * @param size
     * @return
     */
    List<Blog> listBlogTop(Integer size);

    /**
     * 查询所有
     *
     * @return
     */
    List<Blog> findAll();

    List<Blog> blogQuery(String query);

    List<Blog> blogTypeId(Long id);

    List<Blog> blogTagId(Long id);

    int updateViews(Long id);
}
