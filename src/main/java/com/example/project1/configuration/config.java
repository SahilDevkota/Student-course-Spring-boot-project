package com.example.project1.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class config {


    // We use modelmapper to map from entity to dto and vice versa.
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
