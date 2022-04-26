package com.blog.web.amdin;

import com.blog.pojo.Tag;
import com.blog.pojo.Type;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String list(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model) {
        PageInfo<Tag> tagInfo = tagService.splitPage(page, 5);//默认每页五条数据
        model.addAttribute("tagInfo", tagInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input() {
        return "admin/tags-add";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getOneTag(id));
        return "admin/tags-update";
    }


    @PostMapping("/addtags")
    public String save(Tag tag, RedirectAttributes attributes, Model model) {
        if (tag.getName() == null || tag.getName().equals("")) {
            model.addAttribute("message", "内容不能为空");
        } else {
            Tag t = tagService.getByName(tag.getName());
            if (t != null) {
                model.addAttribute("message", "分类存在");
            } else {
                Long id = tagService.saveTag(tag);
                if (id == null) {
                    attributes.addFlashAttribute("message", "添加失败");
                } else {
                    attributes.addFlashAttribute("message", "添加成功");
                }
                return "redirect:/admin/tags";
            }

        }
        return "admin/tags-add";
    }

    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, @PathVariable Long id, RedirectAttributes attributes, Model model) {
        if (tag.getName() == null || tag.getName().equals("")) {
            model.addAttribute("message", "内容不能为空");
        } else {
            int row = tagService.updateTag(id, tag);
            if (row < 1) {
                attributes.addFlashAttribute("message", "更新失败");
            } else {
                attributes.addFlashAttribute("message", "更新成功");
            }
            return "redirect:/admin/tags";
        }
        return "admin/tags-update";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        int row = tagService.delete(id);
        if (row > 0) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/tags";
    }
}
