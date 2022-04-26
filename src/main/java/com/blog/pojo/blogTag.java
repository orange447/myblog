package com.blog.pojo;

public class blogTag {
    private Long id;
    private Long tagId;
    private Long blogId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "blogTag{" +
                "id=" + id +
                ", tagId=" + tagId +
                ", blogTypeId=" + blogId +
                '}';
    }
}
