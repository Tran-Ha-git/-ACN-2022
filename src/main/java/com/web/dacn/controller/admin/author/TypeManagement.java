package com.web.dacn.controller.admin.author;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TypeManagement {

    @GetMapping("/types")
    public String getType(Model model) {
        return "adminListType";
    }
}