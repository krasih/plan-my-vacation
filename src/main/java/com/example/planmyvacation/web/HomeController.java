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

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/plan")
    public String plan() {

        return "plan";
    }

    @GetMapping("/plan-create")
    public String planCreate() {

        return "plan-create";
    }

    @GetMapping("/plans")
    public String plans() {

        return "plans";
    }

    @GetMapping("/users")
    public String users() {

        return "users";
    }

    @GetMapping("/jobs")
    public String jobs() {

        return "jobs";
    }

    @GetMapping("/contacts")
    public String contacts() {

        return "contacts";
    }

}
