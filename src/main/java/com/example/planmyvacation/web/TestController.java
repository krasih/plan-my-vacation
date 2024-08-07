package com.example.planmyvacation.web;

import com.example.planmyvacation.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/test")
    public String test(Model model) {

        model.addAttribute("userData", userService.getUserById(5L));

        return "test";
    }

}
