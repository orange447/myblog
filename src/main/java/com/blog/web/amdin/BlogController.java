package com.blog.web.amdin;

import com.blog.pojo.Blog;
import com.blog.pojo.User;
import com.blog.pojo.vo.BlogInfoVo;
import com.blog.service.*;
import com.blog.service.impl.CommentServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private CommentService commentService;

    /**
     * 博客首页 无条件分页查询
     *
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String all(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model) {
        PageInfo<Blog> allBlog = blogService.splitPage(page, 4);
        model.addAttribute("page", allBlog);
        model.addAttribute("allType", typeService.getAllType());
        model.addAttribute("blogs", blogService.listBlogTop(8));
        return "admin/blogs";
    }

//    @GetMapping("/blogs")
//    public String blogs(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model, BlogInfoVo blogVo) {
//        model.addAttribute("page", blogService.splitPage(page, 2, blogVo));
//        return "admin/blogs";
//    }

    /**
     * 条件分页查询
     *
     * @param page
     * @param model
     * @param blogVo
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model, BlogInfoVo blogVo) {
        if (blogVo == null) {
            model.addAttribute("page", blogService.splitPage(page, 4));
        } else {
            model.addAttribute("page", blogService.splitPage(page, 4, blogVo));
        }
        return "admin/blogs :: blogList";
    }

    /**
     * 新增博客跳转
     *
     * @param model
     * @return
     */
    @GetMapping("blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("allTag", tagService.getAllTag());
        return "admin/addblog";
    }

    /**
     * 代码段
     *
     * @param model
     */
    private void setTypeAndTag(Model model) {
        model.addAttribute("allTag", tagService.getAllTag());
        model.addAttribute("allType", typeService.getAllType());
    }

    @GetMapping("blogs/{id}/input")
    public String editInput(Model model, @PathVariable Long id) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
//        System.out.println(blog.getDescription());
        /*System.out.println(blog.getTagList());
        System.out.println(blog.getTagIds());*/
        model.addAttribute("blog", blog);
        return "admin/updateblog";
    }

    /**
     * 新增博客
     *
     * @param blog
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes) {
        setBlog(blog, session);
        String[] ids = blog.getTagIds().split(",");
        blog.setTagList(tagService.getByIds(ids));
        Long id = blogService.saveBlog(blog);
        if (id != null) {
            blogTagService.saveBlogTag(blog.getId(), ids);
            attributes.addFlashAttribute("message", "操作成功");
        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 代码段
     *
     * @param blog
     * @param session
     */
    private void setBlog(Blog blog, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(((User) session.getAttribute("user")).getId());
        blog.setType(typeService.getOneType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
    }

    /**
     * 更新博客
     *
     * @param blog
     * @param session
     * @param attributes
     */
    @PostMapping("/updateblogs")
    public String update(Blog blog, HttpSession session, RedirectAttributes attributes) {
        setBlog(blog, session);
        String[] ids = blog.getTagIds().split(",");
        blog.setTagList(tagService.getByIds(ids));
        int id = blogService.updateBlog(blog);
        if (id > 0) {
            blogTagService.updateBlogTag(blog.getId(), ids);
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            attributes.addFlashAttribute("message", "修改失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    @Transactional
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogTagService.deleteById(id);
        commentService.deleteComments(id);
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
