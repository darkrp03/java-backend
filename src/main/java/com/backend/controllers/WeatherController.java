package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherController {

    @Value("${weather.api.key.weatherapi}")
    private String apiKey;

    @Value("${weather.api.url.weatherapi}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        String url = apiUrl + "?key=" + apiKey + "&q=" + city;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            var body = response.getBody();

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching weather data: " + e.getMessage());
        }
    }
}