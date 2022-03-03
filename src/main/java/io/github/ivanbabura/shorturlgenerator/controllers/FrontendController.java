package io.github.ivanbabura.shorturlgenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import io.github.ivanbabura.shorturlgenerator.entities.Url_matching;
import io.github.ivanbabura.shorturlgenerator.services.Url_matching_Service;
import java.util.List;

@Controller
@RequestMapping("/view")
public class FrontendController {
    private final Url_matching_Service service;

    @Autowired
    public FrontendController(Url_matching_Service service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/get")
    public String get() {
        return "/view/get";
    }

    @GetMapping("/create")
    public String create() {
        return "/view/create";
    }

    @GetMapping("/delete")
    public String delete() {
        return "/view/delete";
    }

    @GetMapping("/error")
    public String error() {
        return "/view/error";
    }

    @GetMapping("/show_table")
    public String show_table(Model model) {
        List<Url_matching> url_matching_List = service.findAll();
        model.addAttribute("urlsList", url_matching_List);
        return "/view/show_table";
    }
}
