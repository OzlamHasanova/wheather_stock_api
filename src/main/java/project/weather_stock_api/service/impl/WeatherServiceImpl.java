package project.weather_stock_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.weather_stock_api.dto.response.WeatherDto;
import project.weather_stock_api.dto.response.WeatherResponse;
import project.weather_stock_api.entity.Weather;
import project.weather_stock_api.repository.WeatherRepository;
import project.weather_stock_api.service.WeatherService;
import project.weather_stock_api.service.client.WeatherServiceWithFeignClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherServiceWithFeignClient weatherServiceWithFeignClient;

    @Autowired
private JavaMailSender javaMailSender;


    @Override
    public WeatherDto getWeatherByCityName(String cityName) {


        Optional<Weather> weatherOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdateTimeDesc(cityName);
        if (weatherOptional.isEmpty()) {
            return WeatherDto.convert(getWeatherFromWeatherStack(cityName));
        }
        if(weatherOptional.get().getUpdateTime().isBefore(LocalDateTime.now().minusMinutes(40))){
            return WeatherDto.convert(getWeatherFromWeatherStack(cityName));
        }
        WeatherDto weatherDto=WeatherDto.convert(weatherOptional.get());
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("7qk9bme@code.edu.az");
        mailMessage.setTo(getEmail());
        mailMessage.setText("Today in city "+cityName+" weather temperature is "+weatherDto.getTemperature());;
        mailMessage.setSubject("weather");
        javaMailSender.send(mailMessage);
        return weatherDto;
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
    public String getEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
           return null;

    }
}

