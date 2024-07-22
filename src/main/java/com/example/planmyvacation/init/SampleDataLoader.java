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


        Map<String, String> cityCoordinates = Map.of(
                "Chianti", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d92662.07504340635!2d11.108868854192842!3d43.46707197143615!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x132a3408bba0aa8d%3A0x4082c90e3e5a540!2s53011%20Castellina%20in%20Chianti%2C%20Province%20of%20Siena%2C%20Italy!5e0!3m2!1sen!2sbg!4v1721640663761!5m2!1sen!2sbg",
                "Florence", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d65188.261438256275!2d11.215423315123862!3d43.77407613201832!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x132a56a680d2d6ad%3A0x93d57917efc72a03!2sFlorence%2C%20Metropolitan%20City%20of%20Florence%2C%20Italy!5e0!3m2!1sen!2sbg!4v1721640524977!5m2!1sen!2sbg",
                "Rome", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d159903.9263046514!2d12.382554776044776!3d41.866341630890346!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x13258a111bd74ac3%3A0x3094f9ab2388100!2sMetropolitan%20City%20of%20Rome%20Capital%2C%20Italy!5e0!3m2!1sen!2sbg!4v1721640443280!5m2!1sen!2sbg",
                "Barcelona", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d95780.47787015322!2d2.057788801385641!3d41.39276734229955!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12a49816718e30e5%3A0x44b0fb3d4f47660a!2sBarcelona%2C%20Spain!5e0!3m2!1sen!2sbg!4v1719850383198!5m2!1sen!2sbg",
                "Canary Islands", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1272433.594401552!2d-16.74998303037581!3d28.24877241615477!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xc41aa86ef755363%3A0x10340f3be4bc8c0!2sCanary%20Islands%2C%20Spain!5e0!3m2!1sen!2sbg!4v1721640360895!5m2!1sen!2sbg",
                "Seville", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d50726.511922697464!2d-5.9990043101641!3d37.38020618634614!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd126c1114be6291%3A0x34f018621cfe5648!2sSeville%2C%20Spain!5e0!3m2!1sen!2sbg!4v1721640304913!5m2!1sen!2sbg",
                "Cardiff", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d79522.71780565252!2d-3.228741665910496!3d51.47495532414542!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x486e02d434ec53f5%3A0x143406db6586670e!2sCardiff%2C%20UK!5e0!3m2!1sen!2sbg!4v1721640147914!5m2!1sen!2sbg",
                "Edinburgh", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d71487.10339036374!2d-3.295989701381428!3d55.94985754599834!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4887b800a5982623%3A0x64f2147b7ce71727!2sEdinburgh%2C%20UK!5e0!3m2!1sen!2sbg!4v1721640067463!5m2!1sen!2sbg",
                "London", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d94496.30293818022!2d-0.18075991855563694!3d51.51000397057365!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47d8a00baf21de75%3A0x52963a5addd52a99!2sLondon%2C%20UK!5e0!3m2!1sen!2sbg!4v1721639960186!5m2!1sen!2sbg"
        );

        List<City> allCities = cityRepository.findAll();

        List<Location> locations = allCities.stream()
                .map(city -> new Location()
                        .setCountry(city.getCountry())
                        .setCity(city)
                        .setGpxCoordinates(cityCoordinates.get(city.getName()))
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
                        .setCategories(Set.of(cat_visit.get(getRandom(0, cat_visit.size() - 1))))
                        .setRating(getRandom(1.0, 5.0))
                        .setName(faker.location().building())
                        .setDescription(String.join(" ", faker.lorem().sentences(5)))
                        .setImageUrl("")  /*TODO: add some images here*/
                )
                .toList();

        placeRepository.saveAll(placesToVisit);

        List<Place> placesToEat = IntStream.rangeClosed(1, 15)
                .mapToObj(i -> new Place()
                        .setLocation(barcelona)
                        .setType(PlaceType.EAT)
                        .setCategories(Set.of(cat_eat.get(getRandom(0, cat_eat.size() - 1))))
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

        loadPlan("2023-10-11", "2023-10-17" , "Chianti", "user");
        loadPlan("2024-11-20", "2024-11-24" , "Barcelona", "user");
        loadPlan("2025-12-25", "2025-12-31" , "London", "user");
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
                        .setPlan(plan)
                        .setItinerary(itinerary)
                        .setOrder(i + 1)
                        .setPlace(places.get(getRandom(0, places.size() - 1)));

                currentActivities.add(activity);
            }

            itinerary.setActivities(currentActivities);
            allActivities.addAll(currentActivities);
        }

//        MyPlaces
        List<Place> myPlaces = new ArrayList<>();
        for (int i = 0; i < 4; i++) {

            Place randomPlace = places.get(getRandom(0, places.size() - 1));
            myPlaces.add(randomPlace);
        }

        plan.setMyPlaces(myPlaces);



        planRepository.save(plan);
        itineraryRepository.saveAll(itineraries);
        activityRepository.saveAll(allActivities);
    }




    private void initSampleData() {

        cities_it.forEach(city -> city.setCountry(countries.get(0)).setImageUrl("/images/chianti.jpg"));
        cities_es.forEach(city -> city.setCountry(countries.get(1)).setImageUrl("/images/barcelona.jpg"));
        cities_uk.forEach(city -> city.setCountry(countries.get(2)).setImageUrl("/images/london.jpeg"));

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
