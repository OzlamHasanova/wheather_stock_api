package project.weather_stock_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.weather_stock_api.dto.response.WeatherDto;
import project.weather_stock_api.dto.response.WeatherResponse;
import project.weather_stock_api.repository.WeatherRepository;
import project.weather_stock_api.service.WeatherService;

@RestController
@RequestMapping("/v1/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    @GetMapping("/{city}")
    public WeatherDto getWeatherByCityName(@PathVariable("city") String city){
        return weatherService.getWeatherByCityName(city);
    }

}