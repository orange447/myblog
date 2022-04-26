package com.blog.web;

import com.blog.pojo.Type;
import com.blog.pojo.vo.BlogInfoVo;
import com.blog.pojo.vo.TypeBlogVo;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, Model model, @PathVariable Long id) {
        List<TypeBlogVo> typeList = typeService.listTypeTop(10000);
        if (id == -1) {
            id = typeList.get(0).getId();
        }
        model.addAttribute("types", typeList);
        model.addAttribute("page", blogService.blogTypeId(page, 8, id));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
