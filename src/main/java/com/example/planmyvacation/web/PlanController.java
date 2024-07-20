package com.example.planmyvacation.web;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.service.CountryService;
import com.example.planmyvacation.service.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;
    private final CountryService countryService;

    public PlanController(PlanService planService, CountryService countryService) {
        this.planService = planService;
        this.countryService = countryService;
    }

    @ModelAttribute("planData")
    public PlanCreateDTO createEmptyRegisterDTO() {
        return new PlanCreateDTO();
    }

    @ModelAttribute("countriesData")
    public Map<String, List<CityDTO>> allCountriesAndCities() {

        return countryService.getAllAsMap();
    }

    @GetMapping("/create")
    public String viewPlanCreate() {

        return "plan-create";
    }

    @PostMapping("/create")
    public String doPlanCreate(
            PlanCreateDTO planData
    ) {

        planService.createPlan(planData);

        // TODO: Must redirect to the newly created plan and open it for editing
        return "redirect:/plan";
    }

}
