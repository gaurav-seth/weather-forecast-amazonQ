package com.weather.app.service;

import com.weather.app.model.WeatherData;
import com.weather.app.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherByLocation(String location) {
        try {
            logger.debug("Fetching weather data for location: {}", location);
            
            String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("q", location)
                    .queryParam("appid", apiKey)
                    .queryParam("units", "metric") // Get temperature in Celsius
                    .toUriString();

            logger.debug("API URL: {}", url.replace(apiKey, "***"));

            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

            if (response != null && response.getMain() != null) {
                WeatherData weatherData = new WeatherData(
                    response.getName(),
                    response.getMain().getTemp(),
                    response.getMain().getTempMin(),
                    response.getMain().getTempMax()
                );
                
                logger.debug("Successfully fetched weather data for: {}", response.getName());
                return weatherData;
            } else {
                logger.warn("No weather data found for location: {}", location);
                return new WeatherData("No weather data found for the specified location.");
            }

        } catch (HttpClientErrorException.NotFound e) {
            logger.warn("Location not found: {}", location);
            return new WeatherData("Location not found. Please check the spelling and try again.");
        } catch (HttpClientErrorException.Unauthorized e) {
            logger.error("API key is invalid or missing");
            return new WeatherData("Weather service is currently unavailable. Please try again later.");
        } catch (Exception e) {
            logger.error("Error fetching weather data for location: {}", location, e);
            return new WeatherData("Unable to fetch weather data. Please try again later.");
        }
    }
}