package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.PlansSummaryPages;
import com.example.planmyvacation.model.convert.PlanDTOConverter;
import com.example.planmyvacation.model.dto.*;
import com.example.planmyvacation.model.entity.*;
import com.example.planmyvacation.repository.LocationRepository;
import com.example.planmyvacation.repository.PlanRepository;
import com.example.planmyvacation.repository.UserRepository;
import com.example.planmyvacation.service.ActivityService;
import com.example.planmyvacation.service.ItineraryService;
import com.example.planmyvacation.service.PlaceService;
import com.example.planmyvacation.service.PlanService;
import com.example.planmyvacation.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanDTOConverter converter;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final PlaceService placeService;

    public PlanServiceImpl(
            PlanDTOConverter converter,
            LocationRepository locationRepository,
            UserRepository userRepository,
            PlanRepository planRepository,
            PlaceService placeService
    ) {

        this.converter = converter;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.placeService = placeService;
    }

    @Override
    public Long createPlan(PlanCreateDTO planDTO) {

        Plan plan = setPlan(planDTO);

        Plan saved = planRepository.save(plan);

        return saved.getId();
    }

    @Override
    public void deletePlan(Long id) {

        planRepository.deleteById(id);
    }

    @Override
    public List<PlanSummaryDTO> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Plan> plans = planRepository.findAll(pageable);
        List<Plan> listOfPlans = plans.getContent();

        return listOfPlans.stream()
                .map(converter::mapPlanToPlanSummaryDTO)
                .toList();
    }

    public PlansSummaryPages getAll(int page, int pageSize, String status, String sortBy, String sortOrder) {

        Sort sort = Sort.by("id").ascending();

        if (!sortBy.isEmpty()) {
            sort = Sort.by(sortBy).ascending();
            if ("desc".equals(sortOrder)) sort = Sort.by(sortBy).descending();
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        Page<Plan> plans = planRepository.findAll(pageRequest);

        if ("all".equals(status)) {
            plans = planRepository.findAll(pageRequest);
        }
        else {
            if ("active".equals(status) || status.isEmpty()) plans = planRepository.findAllByActive(pageRequest, true);
            if ("inactive".equals(status)) plans = planRepository.findAllByActive(pageRequest, false);
        }

        List<Plan> listOfPlans = plans.getContent();

        List<PlanSummaryDTO> content = listOfPlans.stream().map(converter::mapPlanToPlanSummaryDTO).toList();

        return new PlansSummaryPages()
                .setContent(content)
                .setPageNo(plans.getNumber())
                .setPageSize(plans.getSize())
                .setTotalElements(plans.getTotalElements())
                .setTotalPages(plans.getTotalPages())
                .setLastPage(plans.isLast());
    }

    @Override
    public PlanDetailsDTO getPlanById(long id) {

        return planRepository
                .findById(id)
                .map(converter::mapPlanToPlanDetailsDTO)
                .orElse(null);
    }

    private User getCurrentUser() {

        User currentUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            currentUser = userRepository.findByUsername(currentUserName).get();
        }

        return currentUser;
    }

    private Plan setPlan(PlanCreateDTO planDTO) {

        Location location = locationRepository.findByCity_Name(planDTO.getCityName());
        Instant startDate = Utils.getInstant(planDTO.getStartDate());
        Instant endDate = Utils.getInstant(planDTO.getEndDate());

        Plan plan = new Plan()
                .setUser(getCurrentUser())
                .setLocation(location)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setActive(!endDate.isBefore(Instant.now()));

        // TODO: Move this in a separate static method and see if the SampleDataLoader.loadPlans() method
        //       still works correctly
        long days = startDate.until(endDate, ChronoUnit.DAYS) + 1;
        List<Itinerary> itineraries = new ArrayList<>();

        for (int day = 0; day < days; day++) {

            Instant currItineraryDate = startDate.plus(day, ChronoUnit.DAYS);

            Itinerary itinerary = new Itinerary()
                    .setPlan(plan)
                    .setDate(currItineraryDate)
                    .setDayNo(day + 1)
                    .setLastDay(day == days - 1);

            itineraries.add(itinerary);
        }

        plan.setItineraries(itineraries);

        return plan;
    }

    @Transactional
    @Override
    public void deleteMyPlace(Long placeId, Long planId) {

        Plan plan = planRepository.findById(planId).get();
        Place place = placeService.getPlace(placeId);
        plan.getMyPlaces().remove(place);

        planRepository.deleteMyPlace(placeId, planId);
    }

    @Transactional
    @Override
    public void addMyPlace(Long placeId, Long planId) {

        planRepository.addMyPlace(placeId, planId);
    }

    @Transactional
    @Override
    public void addItineraryActivity(Long planId, Long itineraryId, Long placeId) {

        planRepository.addItineraryActivity(placeId, itineraryId, planId);
    }

}
