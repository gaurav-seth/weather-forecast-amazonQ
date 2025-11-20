package com.weather.app.controller;

import com.weather.app.model.WeatherData;
import com.weather.app.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String index(Model model) {
        logger.debug("Serving landing page");
        model.addAttribute("weatherData", new WeatherData());
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam("location") String location, Model model) {
        logger.debug("Processing weather request for location: {}", location);
        
        if (location == null || location.trim().isEmpty()) {
            WeatherData errorData = new WeatherData("Please enter a valid location.");
            model.addAttribute("weatherData", errorData);
            model.addAttribute("location", "");
            return "index";
        }

        WeatherData weatherData = weatherService.getWeatherByLocation(location.trim());
        model.addAttribute("weatherData", weatherData);
        model.addAttribute("location", location.trim());
        
        return "index";
    }
}