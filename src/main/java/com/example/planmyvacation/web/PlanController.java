package com.example.planmyvacation.web;

import com.example.planmyvacation.model.PlansSummaryPages;
import com.example.planmyvacation.model.dto.*;
import com.example.planmyvacation.service.CountryService;
import com.example.planmyvacation.service.PlaceService;
import com.example.planmyvacation.service.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/plans")
public class PlanController {

    private final static int DEFAULT_PAGE_SIZE = 15;

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


    @GetMapping()
    public Collection<ModelAndView> getAllPlans(
            @RequestParam(value="page", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value="sortBy", defaultValue = "", required = false) String sortBy,
            @RequestParam(value="order", defaultValue = "asc", required = false) String sortOrder,
            @RequestParam(value="status", defaultValue = "", required = false) String planStatus
    ) {

        PlansSummaryPages plans = planService.getAll(pageNo, DEFAULT_PAGE_SIZE, planStatus, sortBy, sortOrder);

        ModelAndView plansPage = new ModelAndView("plans", Map.of(
                "plansData", plans.getContent(),
                "currentPage", plans.getPageNo(),
                "totalPages", plans.getTotalPages(),
                "planStatus", planStatus
        ));

        ModelAndView plansFragment = new ModelAndView("fragments/tables :: plans", Map.of(
                "plansData", plans.getContent()
        ));

        ModelAndView paginationFragment = new ModelAndView("fragments/tables :: pagination", Map.of(
                "currentPage", plans.getPageNo(),
                "totalPages", plans.getTotalPages(),
                "planStatus", planStatus
        ));


        if (!planStatus.isEmpty()) {
            return List.of( plansFragment, paginationFragment );
        }

        return List.of( plansPage );
    }

    @GetMapping("/create")
    public String viewPlanCreate() {

        return "plan-create";
    }

    @PostMapping("/create")
    public String doPlanCreate(
            PlanCreateDTO planCreateData
    ) {

        Long planId = planService.createPlan(planCreateData);

        return "redirect:/plans/" + planId;
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

        planService.addMyPlace(placeId, planId);

        return "fragments/modals :: empty_card";
    }

    @DeleteMapping("/{planId}/places/{placeId}")
    public String deletePlace(@PathVariable("planId") Long planId, @PathVariable("placeId") Long placeId) {

        planService.deleteMyPlace(placeId, planId);

        return "fragments/sidebar :: empty_place";
    }

    @PutMapping("/{planId}/places/{placeId}/itineraries/{itineraryId}")
    public String addToMyPlaces(@PathVariable("planId") Long planId, @PathVariable("placeId") Long placeId, @PathVariable("itineraryId") Long itineraryId) {

        planService.addItineraryActivity(planId, itineraryId, placeId);
        planService.deleteMyPlace(placeId, planId);
        PlanDetailsDTO plan = planService.getPlanById(planId);

        return "fragments/modals :: empty_card";
    }

}
