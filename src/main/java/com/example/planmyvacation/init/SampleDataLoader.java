package com.example.planmyvacation.init;

import com.example.planmyvacation.model.entity.*;
import com.example.planmyvacation.model.enums.PlaceType;
import com.example.planmyvacation.model.enums.UserRole;
import com.example.planmyvacation.repository.*;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private List<Country> countries = addCountries("Italy", "Spain", "United Kingdom");
    private List<City> cities_it = addCities("Chianti", "Florence", "Rome");
    private List<City> cities_es = addCities("Barcelona", "Canary Islands", "Seville");
    private List<City> cities_uk = addCities("Cardiff", "Edinburgh", "London");

    private final CareerRepository careerRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PlaceRepository placeRepository;
    private final PlanRepository planRepository;
    private final ItineraryRepository itineraryRepository;
    private final ActivityRepository activityRepository;
    private final Faker faker;
    private final Random random;

    public SampleDataLoader(
            CareerRepository careerRepository,
            CountryRepository countryRepository,
            CityRepository cityRepository,
            LocationRepository locationRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            PlaceRepository placeRepository,
            PlanRepository planRepository,
            ItineraryRepository itineraryRepository,
            ActivityRepository activityRepository,
            Faker faker
    ) {
        this.careerRepository = careerRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.locationRepository = locationRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.placeRepository = placeRepository;
        this.planRepository = planRepository;
        this.itineraryRepository = itineraryRepository;
        this.activityRepository = activityRepository;
        this.faker = faker;
        this.random = new Random();
    }

    @Override
    public void run(String... args) throws Exception {

        initSampleData();

        loadCareers();
        loadCountriesAndCities(countries);
        loadLocations();
        loadUsersAndRoles();
        loadCategories();
        loadPlaces();
        loadPlans();
    }

    private void loadCareers() {

        if (careerRepository.count() > 0) return;

        List<Career> careers = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> new Career()
                        .setCategory(faker.job().field())
                        .setTitle(faker.job().title())
                        .setDescription(String.join(" ", faker.lorem().sentences(3)))
                        .setPublished(getRandom(1, 15) + " days ago.")
                )
                .toList();

        careerRepository.saveAll(careers);
    }

    private void loadCountriesAndCities(List<Country> countries) {

        if (countryRepository.count() == 0) countryRepository.saveAll(countries);
    }

    private void loadUsersAndRoles() {

        if (userRepository.count() > 0) return;

        User admin = new User()
                .setUsername("admin")
                .setEmail("admin@email.com")
                .setPassword("admin")
                .setRole(
                        new Role(UserRole.ADMIN)
                );

        userRepository.save(admin);

        User user = new User()
                .setUsername("user")
                .setEmail("user@email.com")
                .setPassword("user")
                .setRole(
                        new Role(UserRole.USER)
                );

        userRepository.save(user);
    }

    private void loadLocations() {

        if (cityRepository.count() == 0 || locationRepository.count() > 0) return;

        List<City> allCities = cityRepository.findAll();

        List<Location> locations = allCities.stream()
                .map(city -> new Location()
                        .setCountry(city.getCountry())
                        .setCity(city)
                )
                .toList();

        locationRepository.saveAll(locations);
    }

    private void loadCategories() {

        if (categoryRepository.count() > 0) return;

        List<String> visit_categories = List.of("Nature & Parks", "Museums", "Art", "Market", "Fountain",
                "Concert hall", "Church", "Cultural center", "Castle");
        List<String> eat_categories = List.of("Restaurant", "Bar", "Cocktail bar", "Tapas bar", "Bar & Grill",
                "Vegan", "Pizza", "Cafe", "Brunch", "Breakfast", "Juice", "Dessert");

        List<Category> categories = visit_categories.stream()
                .map(cat -> new Category()
                        .setCategory(cat)
                        .setLocationType(PlaceType.VISIT)
                )
                .toList();

        categoryRepository.saveAll(categories);

        categories = eat_categories.stream()
                .map(cat -> new Category()
                        .setCategory(cat)
                        .setLocationType(PlaceType.EAT)
                )
                .toList();

        categoryRepository.saveAll(categories);
    }

    private void loadPlaces() {

        if (placeRepository.count() > 0) return;

        Location barcelona = locationRepository.findByCity_Name("Barcelona");
        List<Category> cat_visit = categoryRepository.findAllByPlaceType(PlaceType.VISIT);
        List<Category> cat_eat = categoryRepository.findAllByPlaceType(PlaceType.EAT);

        List<Place> placesToVisit = IntStream.rangeClosed(1, 15)
                .mapToObj(i -> new Place()
                        .setLocation(barcelona)
                        .setType(PlaceType.VISIT)
                        .setCategory(Set.of(cat_visit.get(getRandom(0, cat_visit.size() - 1))))
                        .setRating(getRandom(1.0, 5.0))
                        .setName(faker.location().building())
                        .setDescription(String.join(" ", faker.lorem().sentences(3)))
                        .setImageUrl("")  /*TODO: add some images here*/
                )
                .toList();

        placeRepository.saveAll(placesToVisit);

        List<Place> placesToEat = IntStream.rangeClosed(1, 15)
                .mapToObj(i -> new Place()
                        .setLocation(barcelona)
                        .setType(PlaceType.EAT)
                        .setCategory(Set.of(cat_eat.get(getRandom(0, cat_eat.size() - 1))))
                        .setRating(getRandom(1.0, 5.0))
                        .setName(faker.location().building())
                        .setDescription(String.join(" ", faker.lorem().sentences(3)))
                        .setImageUrl("")  /*TODO: add some images here*/
                )
                .toList();

        placeRepository.saveAll(placesToEat);
    }

    private void loadPlans() {

        if (planRepository.count() > 0) return;

        loadPlan("2023-10-20", "2023-10-27" , "Barcelona", "user");
        loadPlan("2024-10-20", "2024-10-27" , "Barcelona", "user");
        loadPlan("2025-10-20", "2025-10-27" , "Barcelona", "user");
    }

    private void loadPlan(String fromDate, String toDate, String city, String username) {

        String time = "T00:00:00.00Z";
        Instant startDate = Instant.parse(fromDate + time);
        Instant endDate = Instant.parse(toDate + time);
        Location location = locationRepository.findByCity_Name(city);
        User user = userRepository.findByUsername(username).get();

//        Plan
        Plan plan = new Plan(startDate, endDate, location, user)
                .setActive(!endDate.isBefore(Instant.now()));

//        Itineraries
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

//        Activities
        List<Place> places = placeRepository.findAll();

        List<Activity> allActivities = new ArrayList<>();
        for (Itinerary itinerary : itineraries) {

            List<Activity> currentActivities = new ArrayList<>();

            for (int i = 0; i < 3; i++) {

                Activity activity = new Activity()
                        .setItinerary(itinerary)
                        .setOrder(i + 1)
                        .setPlace(places.get(getRandom(0, places.size() - 1)));

                currentActivities.add(activity);
            }

            itinerary.setActivities(currentActivities);
            allActivities.addAll(currentActivities);
        }

        planRepository.save(plan);
        itineraryRepository.saveAll(itineraries);
        activityRepository.saveAll(allActivities);
    }




    private void initSampleData() {

        cities_it.forEach(city -> city.setCountry(countries.get(0)));
        cities_es.forEach(city -> city.setCountry(countries.get(1)));
        cities_uk.forEach(city -> city.setCountry(countries.get(2)));

        countries.get(0).setCities(cities_it);
        countries.get(1).setCities(cities_es);
        countries.get(2).setCities(cities_uk);

    }

    private int getRandom(int min, int max) {

        return random.nextInt(max - min + 1) + min;
    }

    public double getRandom(double min, double max) {

        double rand = min + new Random().nextDouble() * (max - min);
        return Math.floor(rand * 100) / 100;
    }

    private List<Country> addCountries(String... countries) {

        return Arrays.asList(countries).stream()
                .map(Country::new)
                .collect(Collectors.toList());
    }

    private List<City> addCities(String... cities) {

        return Arrays.asList(cities).stream()
                .map(City::new)
                .collect(Collectors.toList());
    }

}
