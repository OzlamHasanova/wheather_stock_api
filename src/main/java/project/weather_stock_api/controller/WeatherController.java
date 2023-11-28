package project.weather_stock_api.controller;

import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import project.weather_stock_api.dto.response.WeatherDto;
import project.weather_stock_api.dto.response.WeatherResponse;
import project.weather_stock_api.repository.WeatherRepository;
import project.weather_stock_api.service.WeatherService;

@RestController
@RequestMapping("/v1/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    private final MessageSource messageSource;

    @GetMapping("/{city}")
    public WeatherDto getWeatherByCityName(@Valid @PathVariable("city") String city, @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false)String lang) throws AddressException {
        return weatherService.getWeatherByCityName(city,lang);
    }


}
