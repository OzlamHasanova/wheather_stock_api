package project.weather_stock_api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import project.weather_stock_api.dto.response.*;
import project.weather_stock_api.entity.Weather;
import project.weather_stock_api.repository.WeatherRepository;
import project.weather_stock_api.service.WeatherService;
import project.weather_stock_api.service.client.WeatherServiceWithFeignClient;
import project.weather_stock_api.util.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherServiceWithFeignClient weatherServiceWithFeignClient;


    @Override
    public WeatherDto getWeatherByCityName(String cityName) {

        Optional<Weather> weatherOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdateTimeDesc(cityName);
        if (weatherOptional.isEmpty()) {
            return WeatherDto.convert(getWeatherFromWeatherStack(cityName));
        }
        if(weatherOptional.get().getUpdateTime().isBefore(LocalDateTime.now().minusMinutes(40))){
            return WeatherDto.convert(getWeatherFromWeatherStack(cityName));
        }

        return WeatherDto.convert(weatherOptional.get());
    }

    private Weather getWeatherFromWeatherStack(String city) {
        String API_ACCESS_KEY = "2ef576001bc87b5ac662544d44a9343b";
        WeatherResponse weatherResponse = weatherServiceWithFeignClient.getWeatherResponseWithFeignClient(API_ACCESS_KEY, city);
        Weather weather = mapping(city,weatherResponse);

        return weather;
    }


    private Weather mapping(String city,WeatherResponse weatherResponse) {
        Weather weather = Weather.builder()
                .requestedCityName(city)
                .cityName(weatherResponse.getLocation().getName())
                .country(weatherResponse.getLocation().getCountry())
                .temperature(weatherResponse.getCurrent().getTemperature())
                .updateTime(LocalDateTime.now())
                .responseLocalTime(LocalDateTime.parse((weatherResponse.getLocation().getLocaltime()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).build();
        weatherRepository.save(weather);
        return weather;

    }
}

