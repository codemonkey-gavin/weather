package com.gavin.weather.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WeatherDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherDataApplication.class, args);
    }
}
