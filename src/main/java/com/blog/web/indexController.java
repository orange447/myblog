package com.blog.web;


import com.blog.NotFoundException;
import com.blog.pojo.Blog;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class indexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model) {
        model.addAttribute("page", blogService.split(page, 4));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("blogs", blogService.listBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model, @RequestParam("query") String query) {
        if (query != null) {
            model.addAttribute("page", blogService.blogQuery(query, page, 3));
            model.addAttribute("query", query);
        } else {
            model.addAttribute("page", blogService.split(page, 3));
        }
        return "search";

    }

    /**
     * 分页条件查询
     *
     * @return
     */
    @GetMapping("/search/{query}")
    public String searchquery(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model, @PathVariable String query) {
        model.addAttribute("page", blogService.blogQuery(query, page, 3));
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String footerBlogs(Model model) {
        model.addAttribute("newblogs", blogService.listBlogTop(3));
        return "_fragments :: newBlogList";
    }
}
