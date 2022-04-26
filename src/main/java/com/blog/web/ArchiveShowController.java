package com.blog.web;

import com.blog.mapper.ArchiveMapper;
import com.blog.pojo.Blog;
import com.blog.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ArchiveShowController {
    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", archiveService.archiveBlog());
        model.addAttribute("blogCount", archiveService.countBlog());
        return "archives";
    }
}
