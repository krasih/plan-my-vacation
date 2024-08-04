package com.example.planmyvacation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlanMyVacationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanMyVacationApplication.class, args);
    }

}
