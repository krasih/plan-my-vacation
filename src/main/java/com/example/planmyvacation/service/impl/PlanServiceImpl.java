package com.example.planmyvacation.service.impl;

import com.example.planmyvacation.model.dto.PlanCreateDTO;
import com.example.planmyvacation.model.entity.Itinerary;
import com.example.planmyvacation.model.entity.Location;
import com.example.planmyvacation.model.entity.Plan;
import com.example.planmyvacation.model.entity.User;
import com.example.planmyvacation.repository.LocationRepository;
import com.example.planmyvacation.repository.PlanRepository;
import com.example.planmyvacation.repository.UserRepository;
import com.example.planmyvacation.service.PlanService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    public PlanServiceImpl(
            LocationRepository locationRepository,
            UserRepository userRepository, PlanRepository planRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    @Override
    public void createPlan(PlanCreateDTO planDTO) {

        // SET Plan

        // TODO: Fill the USER once the Spring Security is implemented
//        Optional<User> currentUser = userRepository.findById(userSession.id());
//        if (currentUser.isEmpty()) return false;
        // TODO: For now just hardcode user="user", (TO DELETE)
        User user = userRepository.findByUsername("user").get();

        Location location = locationRepository.findByCity_Name(planDTO.getCityName());
        Instant startDate = toInstant(planDTO.getStartDate());
        Instant endDate = toInstant(planDTO.getEndDate());

        // TODO: Move this to the mapToEntity() method
        Plan plan = new Plan()
                .setUser(user)
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

        // SET Plan END

        planRepository.save(plan);
    }

//    private PlanCreateDTO mapToEntity(PlanCreateDTO planDTO) {
//
//
//
//        return new Plan()
//                .setLocation()
//                .setStartDate()
//                .setEndDate()
//                .setActive();
//    }

//    private PlanCreateDTO mapToDTO(Plan plan) {
//
//        return new PlanCreateDTO()
//                .setLocation(plan.getLocation())
//                .setStartDate(plan.getStartDate())
//                .setEndDate(plan.getEndDate());
//    }

    private Instant toInstant(LocalDate date) {

        return date.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

}
