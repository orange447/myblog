package com.blog.web.amdin;

import com.blog.pojo.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model) {
        PageInfo<Type> typeInfo = typeService.splitPage(page, 5);//默认每页五条数据
        model.addAttribute("tyInfo", typeInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input() {
        return "admin/types-add";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getOneType(id));
        return "admin/types-update";
    }


    @PostMapping("/addtypes")
    public String save(Type type, RedirectAttributes attributes, Model model) {
        if (type.getName() == null || type.getName().equals("")) {
            model.addAttribute("message", "内容不能为空");
        } else {
            Type t = typeService.getByName(type.getName());
            if (t != null) {
                model.addAttribute("message", "分类存在");
            } else {
                Long id = typeService.saveType(type);
                if (id == null) {
                    attributes.addFlashAttribute("message", "添加失败");
                } else {
                    attributes.addFlashAttribute("message", "添加成功");
                }
                return "redirect:/admin/types";
            }

        }
        return "admin/types-add";
    }

    @PostMapping("/types/{id}")
    public String editPost(Type type, @PathVariable Long id, RedirectAttributes attributes, Model model) {
        if (type.getName() == null || type.getName().equals("")) {
            model.addAttribute("message", "内容不能为空");
        } else {
            int row = typeService.updateType(id, type);
            if (row < 1) {
                attributes.addFlashAttribute("message", "更新失败");
            } else {
                attributes.addFlashAttribute("message", "更新成功");
            }
            return "redirect:/admin/types";
        }
        return "admin/types-update";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        int row = typeService.delete(id);
        if (row > 0) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }
}
