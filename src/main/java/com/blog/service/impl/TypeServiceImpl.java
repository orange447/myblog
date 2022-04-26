package com.blog.service.impl;

import com.blog.NotFoundException;
import com.blog.mapper.TypeMapper;
import com.blog.pojo.Type;
import com.blog.pojo.vo.TypeBlogVo;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 添加数据
     *
     * @param type
     * @return
     */
    @Override
    public Long saveType(Type type) {
        return typeMapper.save(type);
    }

    /**
     * 根据内容查询
     *
     * @param name
     * @return
     */
    @Override
    public Type getByName(String name) {
        return typeMapper.getByName(name);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Type getOneType(Long id) {
        return typeMapper.getOneType(id);
    }

    /**
     * 分页查询
     *
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Type> splitPage(int currentPageNum, int pageSize) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Type> list = typeMapper.findAllTypeDesc();
        return new PageInfo<>(list);
    }

    /**
     * 更新数据
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public int updateType(Long id, Type type) {
        Type t = typeMapper.getOneType(id);
        if (t == null) {
            throw new NotFoundException("不存在该分类");
        }
        BeanUtils.copyProperties(type, t);
        return typeMapper.UpdateType(t, id);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public int delete(Long id) {
        return typeMapper.deleteOne(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.findAllTypeDesc();
    }

    /**
     * 根据传入的数值获取相应的数据
     *
     * @param size
     * @return
     */
    @Override
    public List<TypeBlogVo> listTypeTop(Integer size) {
        return typeMapper.findTop(size);
    }
}
