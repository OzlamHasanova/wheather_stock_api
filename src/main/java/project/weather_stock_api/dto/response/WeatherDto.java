package project.weather_stock_api.dto.response;

import lombok.*;
import project.weather_stock_api.entity.Weather;

import java.time.LocalDateTime;


@Data
@Builder
public class WeatherDto{
       private String cityName;
        private String country;
        private Integer temperature;


public static WeatherDto convert(Weather weather){
        return new WeatherDto(
        weather.getCityName(),
        weather.getCountry(),
        weather.getTemperature());
        }

}
