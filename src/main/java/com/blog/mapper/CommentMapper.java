package com.blog.mapper;

import com.blog.pojo.Comment;
import com.blog.pojo.Tag;
import com.blog.pojo.vo.TagBlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    List<Comment> listCommentByBlogId(Long blogId);

    Long saveComment(Comment comment);


    Comment findOne(Long parentCommentId);

    Long saveCommentParent(Comment comment);

    void deleteById(Long id);
}
