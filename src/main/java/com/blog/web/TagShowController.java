package com.blog.web;

import com.blog.pojo.Blog;
import com.blog.pojo.vo.TagBlogVo;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model, @PathVariable Long id) {
        List<TagBlogVo> tagList = tagService.listTagTop(10000);
        if (id == -1) {
            id = tagList.get(0).getId();
        }
        model.addAttribute("tags", tagList);
        model.addAttribute("page", blogService.blogTagId(page, 8, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
