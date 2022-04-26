package com.blog.service.impl;

import com.blog.mapper.CommentMapper;
import com.blog.pojo.Comment;
import com.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 根据博客id查询评论
     *
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> commentList = commentMapper.listCommentByBlogId(blogId);
//        return null;
        return eachComment(commentList);
    }

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    @Override
    public Long saveComment(Comment comment) {
        Long parentCommentId = comment.getParentCommentId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentMapper.findOne(parentCommentId));
            comment.setCreate_time(new Date());
        } else {
            comment.setParentComment(null);
            comment.setCreate_time(new Date());
            return commentMapper.saveComment(comment);
        }
        return commentMapper.saveCommentParent(comment);
    }

    /**
     * 删除评论
     * @param id
     */
    @Override
    public void deleteComments(Long id) {
        commentMapper.deleteById(id);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层到第一级子代集合中
        combinedChildren(commentsView);
        return commentsView;
    }

    /**
     * @param comments root根节点,blog不为空的对象集合
     */
    private void combinedChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时缓存区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出所有的子代集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 递归
     *
     * @param comment 被递归的对象
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                /*tempReplys.add(reply);*/
                /*if (reply.getReplyComments().size() > 0) {*/
                    recursively(reply);
                /*}*/
            }
        }
    }
}
