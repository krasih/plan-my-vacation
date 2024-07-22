package com.example.planmyvacation.web;

import com.example.planmyvacation.model.entity.Plan;
import com.example.planmyvacation.repository.PlanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class TestController {

    private final PlanRepository planRepository;

    public TestController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @GetMapping("/test")
    public String test() {

        Plan plan = planRepository.findById(1L).get();

        return "test";
    }

}
