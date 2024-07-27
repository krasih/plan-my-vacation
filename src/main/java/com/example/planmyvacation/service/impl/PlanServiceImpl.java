package com.example.planmyvacation.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final ItineraryService itineraryService;
    private final ActivityService activityService;
    private final PlaceService placeService;

    public PlanServiceImpl(
            LocationRepository locationRepository,
            UserRepository userRepository,
            PlanRepository planRepository,
            ItineraryService itineraryService,
            ActivityService activityService, PlaceService placeService) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.itineraryService = itineraryService;
        this.activityService = activityService;
        this.placeService = placeService;
    }

    @Override
    public Long createPlan(PlanCreateDTO planDTO) {

        Plan plan = setPlan(planDTO);

        Plan saved = planRepository.save(plan);

        return saved.getId();
    }

    @Override
    public List<PlanSummaryDTO> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Plan> plans = planRepository.findAll(pageable);
        List<Plan> listOfPlans = plans.getContent();

        return listOfPlans.stream()
                .map(this::mapToSummaryDTO)
                .toList();
    }

    @Override
    public PlanDetailsDTO getPlanById(long id) {

        return planRepository
                .findById(id)
                .map(this::mapToDetailsDTO)
                .orElse(null);
    }

    //    TODO: If need to use this method rises in more classes, consider adding it in some Utils class maybe...
    private User getCurrentUser() {

        // TODO: Fill the USER once the Spring Security is implemented
//        Optional<User> currentUser = userRepository.findById(userSession.id());
//        if (currentUser.isEmpty()) return false;
        // TODO: For now just hardcode user="user", (TO DELETE)
        return userRepository.findByUsername("user").get();
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

    private PlanDetailsDTO mapToDetailsDTO(Plan plan) {

        LocalDate startDate = Utils.getLocalDate(plan.getStartDate());
        LocalDate endDate = Utils.getLocalDate(plan.getEndDate());

        List<PlaceDTO> myPlaces = plan.getMyPlaces().stream()
                .map(placeService::mapToDTO)
                .toList();

        List<ItineraryDTO> itineraries = plan.getItineraries()
                .stream()
                .map(itineraryService::mapToDTO)
                .toList();

        return new PlanDetailsDTO()
                .setId(plan.getId())
                .setLocation(plan.getLocation())
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setMyPlaces(myPlaces)
                .setItineraries(itineraries)
                .setUser(plan.getUser())
                .setActive(plan.isActive());
    }

    private PlanSummaryDTO mapToSummaryDTO(Plan plan) {

        LocalDate startDate = Utils.getLocalDate(plan.getStartDate());
        LocalDate endDate = Utils.getLocalDate(plan.getEndDate());

        return new PlanSummaryDTO()
                .setId(plan.getId())
                .setCity(plan.getLocation().getCity().getName())
                .setCountry(plan.getLocation().getCountry().getName())
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setUser(plan.getUser())
                .setActive(plan.isActive());
    }

}
