package com.blog.service;

import com.blog.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    void deleteComments(Long id);

    Long saveComment(Comment comment);
}
