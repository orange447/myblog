package com.blog.mapper;

import com.blog.pojo.Tag;
import com.blog.pojo.vo.TagBlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    /**
     * 新增数据
     *
     * @param tag
     * @return
     */
    Long save(Tag tag);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Tag getOneTag(Long id);

    /**
     * 更新数据
     *
     * @param tag
     * @param id
     * @return
     */
    int UpdateTag(Tag tag, @Param("i") Long id);

    /**
     * 分页查询（降序）
     *
     * @return
     */
    List<Tag> findAllTagDesc();

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteOne(Long id);

    /**
     * 根据标签名查询
     */
    Tag getByName(String name);

    List<Tag> getByIds(String[] ids);

    /**
     * 根据传入数值查询标签
     */
    List<TagBlogVo> findTop(Integer size);
}
