package com.example.planmyvacation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/locations")
public class LocationController {


    @GetMapping({"", "/"})
    public String viewLocations() {

        return "locations";
    }

}
