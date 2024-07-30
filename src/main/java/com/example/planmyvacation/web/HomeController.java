package com.example.planmyvacation.web;

import com.example.planmyvacation.model.user.AppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/", "/index"})
    public String getIndex(@AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails instanceof AppUserDetails appUserDetails) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String getHome(@AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails instanceof AppUserDetails appUserDetails) {
            return "home";
        }

        return "redirect:/";
    }

    @GetMapping("/users")
    public String getUsers() {

        return "users";
    }

    @GetMapping("/statistics")
    public String getStatistics() {

        return "statistics";
    }

    @GetMapping("/contacts")
    public String getContacts() {

        return "contacts";
    }

    @GetMapping("/jobs")
    public String getJobs() {

        return "jobs";
    }

    @GetMapping("/about")
    public String getAbout() {

        return "about";
    }

}
