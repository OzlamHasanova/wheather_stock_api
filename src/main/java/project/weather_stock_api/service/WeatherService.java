package project.weather_stock_api.service;

import org.springframework.cloud.openfeign.FeignClient;
import project.weather_stock_api.dto.response.WeatherDto;
import project.weather_stock_api.dto.response.WeatherResponse;

public interface WeatherService {
    WeatherDto getWeatherByCityName(String cityName);
}
