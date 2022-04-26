package com.blog.service;

import com.blog.pojo.Type;
import com.blog.pojo.vo.TypeBlogVo;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface TypeService {
    Long saveType(Type type);

    Type getOneType(Long id);

    Type getByName(String name);

    PageInfo<Type> splitPage(int currentPageNum, int pageSize);

    int updateType(Long id, Type type);

    int delete(Long id);

    List<TypeBlogVo> listTypeTop(Integer size);

    List<Type> getAllType();
}
