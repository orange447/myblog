package com.blog.service.impl;

import com.blog.NotFoundException;
import com.blog.mapper.TagMapper;
import com.blog.mapper.TypeMapper;

import com.blog.pojo.Tag;
import com.blog.pojo.vo.TagBlogVo;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加数据
     *
     * @param tag
     * @return
     */
    @Override
    public Long saveTag(Tag tag) {
        return tagMapper.save(tag);
    }

    /**
     * 根据内容查询
     *
     * @param name
     * @return
     */
    @Override
    public Tag getByName(String name) {
        return tagMapper.getByName(name);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Tag getOneTag(Long id) {
        return tagMapper.getOneTag(id);
    }

    /**
     * 分页查询
     *
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Tag> splitPage(int currentPageNum, int pageSize) {
        PageHelper.startPage(currentPageNum, pageSize);
        List<Tag> list = tagMapper.findAllTagDesc();
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
    public int updateTag(Long id, Tag type) {
        Tag t = tagMapper.getOneTag(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(type, t);
        return tagMapper.UpdateTag(t, id);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public int delete(Long id) {
        return tagMapper.deleteOne(id);
    }

    /**
     * 获得所有标签
     *
     * @return
     */
    @Override
    public List<Tag> getAllTag() {
        return tagMapper.findAllTagDesc();
    }

    /**
     * 根据id数组获取tag
     *
     * @return
     */
    @Override
    public List<Tag> getByIds(String[] ids) {
        tagMapper.getByIds(ids);
        return null;
    }

    @Override
    public List<TagBlogVo> listTagTop(Integer size) {
        return tagMapper.findTop(size);
    }
}
