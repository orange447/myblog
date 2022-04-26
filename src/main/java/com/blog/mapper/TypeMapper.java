package com.blog.mapper;

import com.blog.pojo.Type;
import com.blog.pojo.vo.TypeBlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    /**
     * 新增数据
     *
     * @param type
     * @return
     */
    Long save(Type type);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Type getOneType(Long id);

    /**
     * 更新数据
     *
     * @param type
     * @param id
     * @return
     */
    int UpdateType(Type type,@Param("i") Long id);

    /**
     * 分页查询（降序）
     *
     * @return
     */
    List<Type> findAllTypeDesc();

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteOne(Long id);

    /**
     * 根据分类名查询
     */
    Type getByName(String name);

    List<TypeBlogVo> findTop(Integer size);
}
