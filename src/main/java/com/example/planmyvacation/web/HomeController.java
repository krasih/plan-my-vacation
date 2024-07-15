package com.example.planmyvacation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/#", "/home"})
    public String home() {

        return "home";
    }

    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }

    @GetMapping("/test2")
    public String test2() {

        return "test2";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

}
