package com.example.planmyvacation.config;

import net.datafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
