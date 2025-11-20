package com.weather.app.controller;

import com.weather.app.model.WeatherData;
import com.weather.app.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void testIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("weatherData"));
    }

    @Test
    void testGetWeatherWithValidLocation() throws Exception {
        WeatherData mockWeatherData = new WeatherData("London", 20.0, 15.0, 25.0);
        when(weatherService.getWeatherByLocation(anyString())).thenReturn(mockWeatherData);

        mockMvc.perform(post("/weather")
                .param("location", "London"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("weatherData"))
                .andExpect(model().attribute("location", "London"));
    }

    @Test
    void testGetWeatherWithEmptyLocation() throws Exception {
        mockMvc.perform(post("/weather")
                .param("location", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("weatherData"));
    }
}