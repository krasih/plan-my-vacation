package com.example.planmyvacation.web;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(name = "/plans")
public class PlanController {

//    private final PlanService planService;
    private final CountryService countryService;

    public PlanController(CountryService countryService) {
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

    @GetMapping("/plans/create")
    public String viewPlanCreate() {

        return "plan-create";
    }

//    @PostMapping("/create")
//    public String doPlanCreate() {
//
//        return "plans";
//    }

}
