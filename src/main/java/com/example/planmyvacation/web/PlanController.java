package com.example.planmyvacation.web;

import com.example.planmyvacation.model.dto.CityDTO;
import com.example.planmyvacation.model.dto.PlaceDTO;
import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.model.dto.PlanDetailsDTO;
import com.example.planmyvacation.service.CountryService;
import com.example.planmyvacation.service.PlaceService;
import com.example.planmyvacation.service.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;
    private final CountryService countryService;
    private final PlaceService placeService;

    public PlanController(
            PlanService planService,
            CountryService countryService,
            PlaceService placeService) {
        this.planService = planService;
        this.countryService = countryService;
        this.placeService = placeService;
    }

    @ModelAttribute("planCreateData")
    public PlanCreateDTO createEmptyPlanDTO() {
        return new PlanCreateDTO();
    }

    @ModelAttribute("countriesData")
    public Map<String, List<CityDTO>> allCountriesAndCities() {

        return countryService.getAllAsMap();
    }

//    @ModelAttribute("planDetailsData")
//    public PlanCreateDTO createEmptyRegisterDTO() {
//        return new PlanDetailsDTO();
//    }

    @GetMapping("/create")
    public String viewPlanCreate() {

        return "plan-create";
    }

    @PostMapping("/create")
    public String doPlanCreate(
            PlanCreateDTO planCreateData
    ) {

        planService.createPlan(planCreateData);

        // TODO: Must redirect to the newly created plan and open it for editing
        return "redirect:/plan";
    }

    @GetMapping("/{id}")
    public String viewPlan(@PathVariable Long id, Model model) {

        PlanDetailsDTO plan = planService.getPlanById(id);
        List<PlaceDTO> allPlaces = placeService.findAllByLocation(plan.getLocation().getId());

        model.addAttribute("planDetailsData", plan);
        model.addAttribute("allPlacesData", allPlaces);

        return "plan";
    }

    @PostMapping("/{planId}/places/{placeId}")
    public String addToMyPlaces(@PathVariable("planId") Long planId, @PathVariable("placeId") Long placeId) {

        planService.addPlace(placeId, planId);

        return "fragments/modals :: empty_card";
    }

    @DeleteMapping("/{planId}/places/{placeId}")
    public String deletePlace(@PathVariable("planId") Long planId, @PathVariable("placeId") Long placeId) {

        planService.deletePlace(placeId, planId);

        return "redirect:/plans/" + planId;
    }



}
