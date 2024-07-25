package com.example.planmyvacation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    TODO: Merge the getIndex and getHome methods into one
    @GetMapping({"", "/", "/index"})
    public String getIndex() {

        return "index";
    }

    @GetMapping("/home")
    public String getHome() {

        return "home";
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
