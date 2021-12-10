package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hello", "Hello world!");
        return "index";
    }
}
