package com.blog.pojo.vo;

public class TagBlogVo {
    private Long id;
    private String name;
    private Integer totalBlog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalBlog() {
        return totalBlog;
    }

    public void setTotalBlog(Integer totalBlog) {
        this.totalBlog = totalBlog;
    }

    @Override
    public String toString() {
        return "TypeBlogVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalBlog=" + totalBlog +
                '}';
    }
}
