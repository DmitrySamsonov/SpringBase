package com.example.springbase.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @GetMapping("/")
    public String index(Model model) {
        if (jdbcUrl.contains(";")) {
            jdbcUrl = jdbcUrl.split(";")[0];
        }
        model.addAttribute("jdbcUrl", jdbcUrl);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "mainPage";
    }


}
