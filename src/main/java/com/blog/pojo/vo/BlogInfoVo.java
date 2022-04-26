package com.blog.pojo.vo;

public class BlogInfoVo {
    private String title;
    private Long typeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "BlogInfoVo{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
